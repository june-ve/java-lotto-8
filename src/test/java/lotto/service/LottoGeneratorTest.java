package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    void 로또_번호는_6개여야_한다() {
        LottoGenerator generator = new LottoGenerator();
        Lotto lotto = generator.generateOneLotto();
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 로또_번호는_1부터_45사이의_중복없는_숫자여야_한다() {
        LottoGenerator generator = new LottoGenerator();
        Lotto lotto = generator.generateOneLotto();
        List<Integer> numbers = lotto.getNumbers();

        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers).allMatch((number -> number >= 1 && number <= 45));
    }

    @Test
    void 지정된_개수만큼_로또를_발행한다() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateMultipleLottos(5);

        assertThat(lottos).hasSize(5);
        assertThat(lottos).allSatisfy(lotto ->
                assertThat(lotto.getNumbers()).hasSize(6)
        );
    }
}
