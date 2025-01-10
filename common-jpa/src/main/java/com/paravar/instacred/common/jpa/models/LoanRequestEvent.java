package com.paravar.instacred.common.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "loan_request_events")
@NoArgsConstructor
public class LoanRequestEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto increment
    private Long id;
     // we'll generate...it may be in another database
    @Column(name = "event_id")
    private String eventId;

    @Column(name = "loan_request_id")
    // no foreign key reference ...it may be in another database
    private Long loanRequestId;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public LoanRequestEvent(String eventId, Long loanRequestId, String panNo, double loanAmount) {
        this.eventId = eventId;
        this.loanRequestId = loanRequestId;
        this.panNo = panNo;
        this.loanAmount = loanAmount;
    }
}
