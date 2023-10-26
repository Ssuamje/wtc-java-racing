package racingcar.game;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.Validatable;
import racingcar.Validator;

import java.util.List;

/**
 * 게임 객체
 */
public class RacingGame implements Validatable {

	private final List<String> carNames;
	private final int tryCount;
	private final int minNum;
	private final int thresholdToMove;
	private final int maxNum;

	private static final int DEFAULT_POSITION = 0;

	/**
	 * 외부에서 주어지는 상수 값 또한 해당 게임에서 사용되는 경계값이므로 생성자에 포함하였다.
	 */
	public RacingGame(List<String> carNames, int tryCount, int minNum, int thresholdToMove, int maxNum) {
		this.carNames = carNames;
		this.tryCount = tryCount;
		this.minNum = minNum;
		this.thresholdToMove = thresholdToMove;
		this.maxNum = maxNum;
		Validator.throwIfNotValid(this, ExceptionStatus.INVALID_RACING_GAME);
	}

	/**
	 * 외부에서 주어지는 상수 값은 유효성 검사를 하지 않았다.
	 * 해당 부분은 게임을 하는 사용자에게 주어지는 통제 변수가 아니므로 제외했다.
	 * 만약 해당 부분으로 인해 에러가 난다면 그 부분은 프로그램 자체에 책임이 있는 것이다.
	 */
	@Override
	public boolean isValid() {
		return !this.carNames.isEmpty()
			&&  this.tryCount > 0;
	}

	/**
	 * throw시에 사용되는 ToString의 경우 사용자에게 노출되지 않고,
	 * 프로그래머가 확인하는 부분이므로 상수 또한 포함시켰다.
	 */
	@Override
	public String toString() {
		return "RacingGame{" +
			"carNames=" + carNames +
			", tryCount=" + tryCount +
			", minNum=" + minNum +
			", thresholdToMove=" + thresholdToMove +
			", maxNum=" + maxNum +
			'}';
	}

	/**
	 * Car 객체로 만들어서 오류가 나는지 안 나는지는 해당 게임의 실행 시에 결정된다고 생각했다.
	 * 따라서 Car 객체를 만드는 부분은 게임을 실행하는 메소드 내부로 넣었다.
	 */
	public void runGame() {
		List<Car> cars = this.carNames.stream()
				.map(name -> new Car(name, DEFAULT_POSITION))
				.toList();

		for (int i = 0; i < tryCount; i++) {
			cars.forEach(car -> {
				int randomNum = Randoms.pickNumberInRange(minNum, maxNum);
				if (isGreaterThanThresholdToMove(randomNum)) {
					car.move();
				}
			});
			printMoveResult(cars);
		}
		printWinner(cars);
	}

	private boolean isGreaterThanThresholdToMove(int num) {
		return num >= thresholdToMove;
	}

	private void printMoveResult(List<Car> cars) {
		for (Car car : cars) {
			System.out.println(car.getName() + " : " + "-".repeat(car.getMoveCount()));
		}
		System.out.println();
	}

	private void printWinner(List<Car> cars) {
		int maxMoveCount = cars.stream()
				.mapToInt(Car::getMoveCount)
				.max()
				.orElse(0);
		List<String> winners = cars.stream()
				.filter(car -> car.getMoveCount() == maxMoveCount)
				.map(Car::getName)
				.toList();
		System.out.println("최종 우승자 : " + String.join(", ", winners));
	}
}
