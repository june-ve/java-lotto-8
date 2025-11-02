package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    UNRANKED(0, false, 0);

    private final int matchCount;
    private final boolean bonusRequired;
    private final int prize;

    Rank(int matchCount, boolean bonusRequired, int prize) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
    }

    /**
     * 주어진 일치 개수와 보너스 번호 일치 여부를 기반으로 적절한 등수를 반환한다.
     *
     * <p>등수 판별 규칙:
     * <ul>
     *   <li>6개 일치 → 1등</li>
     *   <li>5개 + 보너스 일치 → 2등</li>
     *   <li>5개 일치 → 3등</li>
     *   <li>4개 일치 → 4등</li>
     *   <li>3개 일치 → 5등</li>
     *   <li>그 외 → 미당첨</li>
     * </ul>
     *
     * @param matchCount 일치한 번호 개수
     * @param bonusMatched 보너스 번호 일치 여부
     * @return 조건에 맞는 등수 Rank, 없으면 UNRANKED
     */
    public static Rank from(long matchCount, boolean bonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> !rank.bonusRequired || bonusMatched)
                .findFirst()
                .orElse(UNRANKED);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
