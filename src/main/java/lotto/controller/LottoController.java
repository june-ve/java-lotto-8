package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoResultAnalyzer;
import lotto.service.LottoService;
import lotto.service.ProfitCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {

    private final LottoService lottoService = new LottoService();
    private final LottoResultAnalyzer analyzer = new LottoResultAnalyzer();
    private final ProfitCalculator profitCalculator = new ProfitCalculator();

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        int purchaseAmount = readPurchaseAmountSafely();
        List<Lotto> purchasedLottos = lottoService.buyLottos(purchaseAmount);
        outputView.printPurchasedLottos(purchasedLottos);

        WinningLotto winningLotto = readWinningLottoSafely();

        Map<Rank, Integer> result = analyzer.analyze(purchasedLottos, winningLotto);
        outputView.printStatistics(result);

        long totalPrize = profitCalculator.calculateTotalPrize(result);
        double profitRate = profitCalculator.calculateProfitRate(totalPrize, purchaseAmount);
        outputView.printProfitRate(profitRate);
    }

    private int readPurchaseAmountSafely() {
        while (true) {
            try {
                return inputView.readPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto readWinningLottoSafely() {
        while (true) {
            try {
                return inputView.readWinningLotto();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
