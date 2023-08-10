package com.nexters.keyme.statistics.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long ownerId;
    private long questionId;
    private long solverCount;
    private int ownerScore;
    private double solverAvgScore;
    private double matchRate;

    void addNewScore(int score) {
        solverCount += 1;
        solverAvgScore = (solverAvgScore + score) / solverCount;
        matchRate = calculateMatchRate(ownerScore, solverAvgScore);
    }

    private double calculateMatchRate(int ownerScore, double solverAvgScore) {
        double diff = Math.abs(ownerScore - solverAvgScore);
        return 100 / (diff + 1);
    }
}
