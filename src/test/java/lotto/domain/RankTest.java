package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    void 일치_6개면_1등이다() {
        Rank rank = Rank.from(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 일치_5개_보너스_있으면_2등이다() {
        Rank rank = Rank.from(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 일치_5개_보너스_없으면_3등이다() {
        Rank rank = Rank.from(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 일치_4개면_4등이다() {
        Rank rank = Rank.from(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 일치_3개면_5등이다() {
        Rank rank = Rank.from(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 일치_2개_이하면_미당첨이다() {
        Rank rank = Rank.from(2, false);
        assertThat(rank).isEqualTo(Rank.UNRANKED);
    }
}
