package com.paravar.instacred.loanHub.domain.loanApplication;

import com.paravar.instacred.loanHub.domain.models.LoanApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Setter
@Getter
@Entity
@ToString
@Table(name = "loan_applications")
public class LoanApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_application_id_generator")
    @SequenceGenerator(
            name = "loan_application_id_generator",
            sequenceName = "loan_application_id_seq",
            initialValue = 1,
            allocationSize = 20)
    private Long id;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "pan_no")
    private String panNo;

    private String email;

    @Column(name = "loan_type_id")
    private Long loanTypeId;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Enumerated(EnumType.STRING)
    // @Column(name = "category", columnDefinition = "loan_category_enum")
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private LoanApplicationStatus status;
}
