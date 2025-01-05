package com.paravar.instacred.loanHub.domain.loanTypes;

import static org.springframework.data.domain.Sort.*;

import com.paravar.instacred.common.jpa.models.PageParams;
import com.paravar.instacred.common.jpa.models.PagedResult;
import com.paravar.instacred.loanHub.ApplicationProperties;
import com.paravar.instacred.loanHub.domain.LoanTypeService;
import com.paravar.instacred.loanHub.domain.models.CreateLoanType;
import com.paravar.instacred.loanHub.domain.models.LoanType;
import com.paravar.instacred.loanHub.domain.models.LoanTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    private final LoanTypeMapper mapper;
    private final ApplicationProperties properties;

    @Cacheable(value = "loanTypes", key = "#pageNo")
    public PagedResult<LoanType> getLoanTypes(int pageNo) {
        PageParams pageParams = PageParams.of(pageNo, properties.pageSize());

        Pageable pageable = PageRequest.of(pageParams.pageNo(), pageParams.pageSize(), Direction.ASC, "id");
        Page<LoanType> data = loanTypeRepository.findAll(pageable).map(mapper::map);
        return PagedResult.of(data);
    }

    @Cacheable(value = "loanTypeDetails", key = "#id")
    public LoanType getLoanType(Long id) {
        return loanTypeRepository.findById(id).map(mapper::map).orElseThrow(() -> LoanTypeNotFoundException.of(id));
    }

    @CacheEvict(value = "loanTypes", allEntries = true)
    public LoanType createLoanType(CreateLoanType loanType) {
        var result = loanTypeRepository.save(mapper.toEntity(loanType));
        return mapper.map(result);
    }

    @Caching(
            evict = {@CacheEvict(value = "loanTypes", allEntries = true)},
            put = {@CachePut(value = "loanTypeDetails", key = "#id")})
    public LoanType updateLoanType(Long id, LoanType loanType) {
        if (!loanTypeRepository.existsById(id)) {
            throw LoanTypeNotFoundException.of(id);
        }
        var entity = mapper.toEntity(loanType);
        entity.setId(id);
        loanTypeRepository.save(entity);
        return mapper.map(entity);
    }

    @Caching(
            evict = {
                @CacheEvict(value = "loanTypes", allEntries = true),
                @CacheEvict(value = "loanTypeDetails", key = "#id")
            })
    public void deleteLoanType(Long id) {
        if (!loanTypeRepository.existsById(id)) throw LoanTypeNotFoundException.of(id);
        loanTypeRepository.deleteById(id);
    }
}
