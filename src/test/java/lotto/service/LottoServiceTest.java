package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void 구입금액이_1000원으로_나누어떨어지지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> lottoService.buyLottos(3500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액이_1000원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> lottoService.buyLottos(999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액에_비례해_로또를_발행한다() {
        List<Lotto> lottos = lottoService.buyLottos(5000);
        assertThat(lottos).hasSize(5);
    }

    @Test
    void 발행된_로또는_각각_6개의_번호를_가진다() {
        List<Lotto> lottos = lottoService.buyLottos(5000);
        assertThat(lottos).allSatisfy(lotto ->
                assertThat(lotto.getNumbers()).hasSize(6)
        );
    }
}
