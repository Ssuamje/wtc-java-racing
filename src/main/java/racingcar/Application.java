package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.game.RacingGame;
import racingcar.game.RacingGameConfig;
import racingcar.game.RacingGameStrategy;
import racingcar.game.RacingGameStrategyFactory;

import java.util.Arrays;
import java.util.List;

public class Application {
	private static final int MIN_NUM = 0;
	private static final int THRESHOLD_TO_MOVE = 4;
	private static final int MAX_NUM = 9;

	private static final String FIRST_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private static final String SECOND_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";

	/**
	 * 입력받는 문자열에 대해, 내부적으로 파싱하는 것은 Application의 책임이라고 생각했다.
	 * 이후에, 해당 문자열들에 대한 데이터를 Game이 받아서 처리하도록 하였다. <- 어떤 데이터를 받아야 할지는 Game에 정해져 있으니.
	 * Game은 데이터를 받아서, 게임을 진행하고, 결과를 출력하는 책임을 가지도록 하였다.
	 */
	public static void main(String[] args) {
		Display.putMessage(FIRST_INPUT_MESSAGE);
		List<String> carNames = adaptInputAsStringList(Console.readLine());
		Display.putMessage(SECOND_INPUT_MESSAGE);
		int tryCount = adaptInputAsInt(Console.readLine());

		RacingGameConfig config = new RacingGameConfig(carNames, tryCount);
		RacingGameStrategy strategy = RacingGameStrategyFactory.createRandomsStrategy(MIN_NUM, MAX_NUM, THRESHOLD_TO_MOVE);
		RacingGame racingGame = new RacingGame(config, strategy);
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
		} catch (Exception e) {
			throw new IllegalArgumentException("알 수 없는 오류가 발생했습니다.");
		}
	}
}
