package com.paravar.instacred.instacred.loanHub.domain.loanTypes;

import static org.springframework.data.domain.Sort.*;

import com.paravar.instacred.common.models.PageParams;
import com.paravar.instacred.common.models.PagedResult;
import com.paravar.instacred.instacred.loanHub.ApplicationProperties;
import com.paravar.instacred.instacred.loanHub.domain.models.CreateLoanType;
import com.paravar.instacred.instacred.loanHub.domain.models.LoanType;
import com.paravar.instacred.instacred.loanHub.domain.models.LoanTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    private final LoanTypeMapper mapper;
    private final ApplicationProperties properties;

    public PagedResult<LoanType> getAllLoanTypes(Integer pageNo, Integer pageSize) {

        PageParams pageParams = PageParams.of(pageNo, pageSize, properties.pageSize(), properties.maxPageSize());

        Pageable pageable = PageRequest.of(pageParams.pageNo(), pageParams.pageSize(), Direction.ASC, "id");

        Page<LoanType> data = loanTypeRepository.findAll(pageable).map(mapper::map);
        return PagedResult.of(data);
    }

    public LoanType getLoanType(Long id) {
        return loanTypeRepository.findById(id).map(mapper::map).orElseThrow(() -> LoanTypeNotFoundException.of(id));
    }

    public LoanType createLoanType(CreateLoanType loanType) {
        var result = loanTypeRepository.save(mapper.toEntity(loanType));
        return mapper.map(result);
    }

    public LoanType updateLoanType(Long id, LoanType loanType) {
        if (!loanTypeRepository.existsById(id)) {
            throw LoanTypeNotFoundException.of(id);
        }
        var entity = mapper.toEntity(loanType);
        entity.setId(id);
        loanTypeRepository.save(entity);
        return mapper.map(entity);
    }

    public void deleteLoanType(Long id) {
        if (!loanTypeRepository.existsById(id)) throw LoanTypeNotFoundException.of(id);
        loanTypeRepository.deleteById(id);
    }
}
