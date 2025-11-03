package lotto.service;

import lotto.domain.Lotto;

import java.util.List;

public class LottoService {

    private static final int LOTTO_PRICE = 1000;
    private final LottoGenerator generator = new LottoGenerator();

    public List<Lotto> buyLottos(int money) {
        int count = money / LOTTO_PRICE;
        List<Lotto> lottos = generator.generateMultipleLottos(count);
        return lottos;
    }
}
