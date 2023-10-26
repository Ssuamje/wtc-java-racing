package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.game.ExceptionStatus;
import racingcar.game.RacingGame;

import java.util.Arrays;
import java.util.List;

public class Application {
    private static final int MIN_NUM = 0;
    private static final int THRESHOLD_TO_MOVE = 4;
    private static final int MAX_NUM = 9;

    private static final String FIRST_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String SECOND_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";

    public static void main(String[] args) {
        System.out.println(FIRST_INPUT_MESSAGE);
        final List<String> carNames = adaptInputAsStringList(Console.readLine());
        System.out.println(SECOND_INPUT_MESSAGE);
        final int tryCount = adaptInputAsInt(Console.readLine());

        RacingGame racingGame = new RacingGame(carNames, tryCount, MIN_NUM, THRESHOLD_TO_MOVE, MAX_NUM);
        racingGame.runGame();
    }

    private static List<String> adaptInputAsStringList(String stringsWithCommas) {
        return Arrays.asList(stringsWithCommas.split(","));
    }

    private static int adaptInputAsInt(String numericString) {
        try {
            return Integer.parseInt(numericString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
