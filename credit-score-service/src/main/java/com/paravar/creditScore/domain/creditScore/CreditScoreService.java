package com.paravar.creditScore.domain.creditScore;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreditScoreService {
    private final CreditScoreRepository creditScoreRepository;

}