package com.nexters.keyme.statistics.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Statistic {
    @Id
    private long id;
    private long ownerId;
    private long questionId;
    private long solverCount;
    private double solverAverage;
    private double matchRate;
}
