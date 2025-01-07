package com.paravar.instacred.loanHub.jobs;

import com.paravar.instacred.loanHub.domain.LoanRequestEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Slf4j
@ConditionalOnProperty(name = "loan-hub.out-box-pattern-enabled", havingValue = "true", matchIfMissing = true)
class LoanRequestEventsPublishJob {

    private final LoanRequestEventService loanRequestEventService;

    @Scheduled(cron = "${loan-hub.publish-new-loan-requests-job-cron}")
    @SchedulerLock(name = "publish-new-loan-requests", lockAtMostFor = "2m")
    public void scheduledTask() {
        LockAssert.assertLocked();
        log.info("Publishing New Loan Request Events at {}", Instant.now());
        loanRequestEventService.publishLoanRequestEvents();
    }
}
