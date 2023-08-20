package com.nexters.keyme.domain.test.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_result_code")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestResultCode {
    @Id
    @Column(length = 8)
    private String code;

    private Long resultId;
}
