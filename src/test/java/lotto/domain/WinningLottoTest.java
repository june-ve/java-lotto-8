package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.LongSummaryStatistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @Test
    void 보너스번호가_당첨번호와_중복되면_예외가_발생한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningLotto(lotto, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또가_6개_일치하면_1등이다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank result = winningLotto.determineRank(userLotto);
        assertThat(result).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또가_5개_일치하고_보너스도_일치하면_2등이다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank result = winningLotto.determineRank(userLotto);
        assertThat(result).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또가_5개만_일치하면_3등이다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 10));

        Rank result = winningLotto.determineRank(userLotto);
        assertThat(result).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또가_4개_일치하면_4등이다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 10, 11));

        Rank result = winningLotto.determineRank(userLotto);
        assertThat(result).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 로또가_3개_일치하면_5등이다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

        Rank result = winningLotto.determineRank(userLotto);
        assertThat(result).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 로또가_2개_이하_일치하면_미당첨이다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 10, 11, 12, 13));

        Rank result = winningLotto.determineRank(userLotto);
        assertThat(result).isEqualTo(Rank.UNRANKED);
    }
}
