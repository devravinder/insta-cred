package com.paravar.creditScore.web;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Slf4j
public class CreditScoreController {
    private final CreditScoreService creditScoreService;
    
}