package com.paravar.instacred.loanHub.domain.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "loan_request_events")
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestEvent {
    @Id // not auto generated // we'll generate...it may be in another database
    private String id;

    @Column(name = "loan_request_id")
    // no foreign key reference ...it may be in another database
    private Long loanRequestId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
