package com.paravar.instacred.common.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
