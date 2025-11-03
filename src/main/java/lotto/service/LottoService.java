package lotto.service;

import lotto.domain.Lotto;

import java.util.List;

public class LottoService {

    private static final int LOTTO_PRICE = 1000;
    private final LottoGenerator generator = new LottoGenerator();

    public List<Lotto> buyLottos(int money) {
        validateMoney(money);

        int count = money / LOTTO_PRICE;
        List<Lotto> lottos = generator.generateMultipleLottos(count);
        return lottos;
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 최소 구입 금액은 1,000원입니다.");
        }
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }
}
