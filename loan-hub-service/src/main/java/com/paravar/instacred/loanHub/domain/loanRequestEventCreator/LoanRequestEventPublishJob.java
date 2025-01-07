package com.paravar.instacred.loanHub.domain.loanRequestEventCreator;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class LoanRequestEventPublishJob {
    private final AbstractPatternLoanRequestEventCreatorImpl loanRequestEventCreator;

    @Scheduled(cron = "${loan-hub.publish-new-loan-requests-job-cron}")
    @SchedulerLock(name = "publish-new-loan-requests", lockAtMostFor = "2m")
    public void scheduledTask() {
        LockAssert.assertLocked();
        log.info("Publishing New Loan Request Events at {}", Instant.now());
        loanRequestEventCreator.publishLoanRequestEvents();
    }
}
