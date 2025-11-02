package lotto.domain;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 같을 수 없습니다.");
        }
    }

    /**
     * 주어진 로또와 당첨 번호를 비교해 등수를 계산한다.
     *
     * @param userLotto 사용자가 구매한 로또
     * @return 등수(Rank)
     */
    public Rank determineRank(Lotto userLotto) {
        long matchCount = userLotto.getNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();

        boolean bonusMatched = userLotto.getNumbers().contains(bonusNumber);
        return Rank.from(matchCount, bonusMatched);
    }
}
