package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultAnalyzer {

    public Map<Rank, Integer> analyze(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> result = initializeResultMap();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.determineRank(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private Map<Rank, Integer> initializeResultMap() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        return result;
    }
}
