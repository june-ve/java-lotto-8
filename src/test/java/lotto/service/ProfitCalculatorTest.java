package lotto.service;

import lotto.domain.Rank;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {

    private final ProfitCalculator calculator = new ProfitCalculator();

    @Test
    void 등수별_당첨금액의_총합을_계산한다() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIRST, 1);
        result.put(Rank.SECOND, 1);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 0);
        result.put(Rank.FIFTH, 2);
        result.put(Rank.UNRANKED, 0);

        long totalPrize = calculator.calculateTotalPrize(result);

        assertThat(totalPrize).isEqualTo(2_030_010_000L);
    }

    @Test
    void 수익률을_소수점_둘째자리에서_반올림하여_계산한다() {
        long totalPrize = 6_250;
        int purchaseAmount = 10_000;

        double profitRate = calculator.calculateProfitRate(totalPrize, purchaseAmount);

        assertThat(profitRate).isEqualTo(62.5);
    }

    @Test
    void 수익률이_100퍼센트_초과인_경우에도_정확히_계산된다() {
        long totalPrize = 20_000;
        int purchaseAmount = 10_000;

        double profitRate = calculator.calculateProfitRate(totalPrize, purchaseAmount);

        assertThat(profitRate).isEqualTo(200.0);
    }
}
