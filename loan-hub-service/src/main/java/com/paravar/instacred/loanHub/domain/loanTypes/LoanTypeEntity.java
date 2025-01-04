package com.paravar.instacred.loanHub.domain.loanTypes;

import com.paravar.instacred.loanHub.domain.models.LoanCategoryEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;

@Entity
@Table(name = "loan_types")
@Data
class LoanTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_type_id_generator")
    @SequenceGenerator(
            name = "loan_type_id_generator",
            sequenceName = "loan_type_id_seq",
            initialValue = 1,
            allocationSize = 20)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    // @Column(name = "category", columnDefinition = "loan_category_enum")
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private LoanCategoryEnum category;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "max_loan_amount")
    private double maxLoanAmount;
}
