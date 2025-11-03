package lotto.service;

import lotto.domain.Rank;

import java.util.Map;

public class ProfitCalculator {

    public long calculateTotalPrize(Map<Rank, Integer> result) {
        long totalPrize = 0L;
        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            totalPrize += (long) entry.getKey().getPrize() * entry.getValue();
        }

        return totalPrize;
    }

    public double calculateProfitRate(long totalPrize, int purchaseAmount) {
        double rate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
