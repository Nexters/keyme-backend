package com.nexters.keyme.statistics.domain.model;

import com.nexters.keyme.common.model.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Statistic extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long ownerId;
    private long questionId;
    private long solverCount;
    private int ownerScore;
    private double solverAvgScore;
    private double matchRate;

    public Statistic(long ownerId, long questionId, int ownerScore) {
        this.ownerId = ownerId;
        this.questionId = questionId;
        this.ownerScore = ownerScore;
    }

    public void addNewScore(long solverId, int score) {
        if (solverId == ownerId) {
            return;
        }

        solverCount += 1;
        solverAvgScore = (solverAvgScore * (solverCount - 1) + score) / solverCount;
        matchRate = calculateMatchRate(ownerScore, solverAvgScore);
    }

    private double calculateMatchRate(int ownerScore, double solverAvgScore) {
        double diff = Math.abs(ownerScore - solverAvgScore);
        return 100 / (diff + 1);
    }
}