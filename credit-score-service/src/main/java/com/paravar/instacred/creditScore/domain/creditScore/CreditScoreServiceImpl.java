package com.paravar.instacred.creditScore.domain.creditScore;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.common.domain.models.CreditScoreNotFoundException;
import com.paravar.instacred.creditScore.domain.CreditScoreService;
import com.paravar.instacred.creditScore.domain.models.CreateCreditScoreRequest;
import com.paravar.instacred.creditScore.domain.models.UpdateCreditScoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class CreditScoreServiceImpl implements CreditScoreService {
    private final CreditScoreRepository repository;
    private final CreditScoreMapper mapper;

    public CreditScore create(CreateCreditScoreRequest creditScore) {

        var entity = mapper.toEntity(creditScore);
        entity.setScore(initialCreditScore());

        var res = repository.save(entity);
        return mapper.map(res);
    }

    public CreditScore findByPanNo(String panNo) {
        return repository.findByPanNo(panNo).map(mapper::map).orElseThrow(() -> CreditScoreNotFoundException.of(panNo));
    }

    public void onLoanApproval(UpdateCreditScoreRequest request) {

        repository
                .findByPanNo(request.panNo())
                .map(entity -> {
                    double loanAmount = entity.getTotalLoanAmount() + request.loanAmount();
                    entity.setTotalLoanAmount(loanAmount);
                    entity.setNoOfLoans(entity.getNoOfLoans() + 1);
                    entity.setScore(entity.getScore() - reduceCreditScore(request.loanAmount()));

                    repository.save(entity);

                    return mapper.map(entity);
                })
                .orElseThrow(() -> CreditScoreNotFoundException.of(request.panNo()));
    }

    private int initialCreditScore() {
        return 750;
    }

    private int reduceCreditScore(double loanAmount) {
        if (loanAmount > 100000) return 50;
        else return 30;
    }
}
