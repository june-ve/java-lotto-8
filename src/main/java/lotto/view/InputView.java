package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;

import java.util.Arrays;
import java.util.List;

public class InputView {

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine().trim();

        validateNumber(input);
        int amount = Integer.parseInt(input);

        return amount;
    }

    private void validateNumber(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력할 수 있습니다.");
        }
    }

    public WinningLotto readWinningLotto() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = parseNumbers(Console.readLine());

        System.out.println("\n보너스 번호를 입력해 주세요.");
        String bonusInput = Console.readLine().trim();
        validateNumber(bonusInput);

        int bonusNumber = Integer.parseInt(bonusInput);
        return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자여야 합니다.");
        }
    }
}
