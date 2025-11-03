package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultAnalyzerTest {

    @Test
    void 로또목록을_당첨번호와_비교해_등수별_개수를_집계한다() {
        // given
        LottoResultAnalyzer analyzer = new LottoResultAnalyzer();
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),  // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),  // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 10)),  // 3등
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),  // 4등
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),  // 5등
                new Lotto(List.of(1, 2, 10, 11, 12, 13))  // 미당첨
        );

        // when
        Map<Rank, Integer> result = analyzer.analyze(lottos, winningLotto);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.get(Rank.UNRANKED)).isEqualTo(1);
    }

    @Test
    void 당첨이_없는_경우에도_모든_Rank가_0으로_초기화된다() {
        LottoResultAnalyzer analyzer = new LottoResultAnalyzer();
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        List<Lotto> lottos = List.of(new Lotto(List.of(10, 11, 12, 13, 14, 15)));

        Map<Rank, Integer> result = analyzer.analyze(lottos, winningLotto);

        assertThat(result.get(Rank.FIRST)).isEqualTo(0);
        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(result.get(Rank.UNRANKED)).isEqualTo(1);
    }
}
