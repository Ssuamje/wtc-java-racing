package racingcar.game;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.Validatable;
import racingcar.Validator;

import java.util.List;
import java.util.OptionalInt;

/**
 * 게임 객체
 */
public class RacingGame implements Validatable {
	private static final int DEFAULT_POSITION = 0;

	private final RacingGameConfig config;
	private final int minNum;
	private final int thresholdToMove;
	private final int maxNum;


	/**
	 * 외부에서 주어지는 상수 값 또한 해당 게임에서 사용되는 경계값이므로 생성자에 포함하였다.
	 */
	public RacingGame(RacingGameConfig config, int minNum, int thresholdToMove, int maxNum) {
		this.config = config;
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
		return this.config.isValid();
	}

	/**
	 * throw시에 사용되는 ToString의 경우 사용자에게 노출되지 않고,
	 * 프로그래머가 확인하는 부분이므로 상수 또한 포함시켰다.
	 */
	@Override
	public String toString() {
		return "RacingGame{" +
			"carNames=" + this.config.getCarNames() +
			", tryCount=" + this.config.getTryCount() +
			", minNum=" + minNum +
			", thresholdToMove=" + thresholdToMove +
			", maxNum=" + maxNum +
			'}';
	}

	/**
	 * Car 객체로 만들어서 오류가 나는지 안 나는지는 해당 게임의 실행 시에 결정된다고 생각했다.
	 * 따라서 Car 객체를 만드는 부분은 게임을 실행하는 메소드 내부로 넣었다. <- 뺀다면 CarFactory를 이용할 것 같다.
	 *
	 * 경우에 따라서 내부적으로 this의 변수를 참조하는 부분을 매개변수로 통제할 수 있겠지만,
	 * 조금 과한 것 같아서 그냥 사용했다.
	 *
	 * 움직일지 말지에 대한 부분을 별도의 클래스로 작성할 수는 있으나,
	 * 불필요하다고 생각해서 분리하지 않았다.
	 *
	 * forEach를 통해 해당 원소들에 side-effect가 발생하지만,
	 * 예외가 되는 상황이 존재하지 않으므로 forEach를 이용했고, 굳이 깊은 복사를 통한 처리는 하지 않았다.
	 */
	public void runGame() {
		List<Car> cars = createCarsByNames(this.config.getCarNames(), DEFAULT_POSITION);

		for (int i = 0; i < tryCount; i++) {
			cars.forEach(car -> {
				int randomNum = Randoms.pickNumberInRange(this.minNum, this.maxNum);
				if (randomNum >= this.thresholdToMove) {
					car.move();
				}
			});
			printMoveResult(cars);
		}
		printWinner(cars);
	}

	private List<Car> createCarsByNames(List<String> carNames, int defaultPosition) {
		return carNames.stream()
			.map(name -> new Car(name, defaultPosition))
			.toList();
	}

	private void printMoveResult(List<Car> cars) {
		for (Car car : cars) {
			System.out.println(car.getName() + " : " + "-".repeat(car.getMoveCount()));
		}
		System.out.println();
	}

	private void printWinner(List<Car> cars) {
		int maxMoveCount = getMaxMoveCount(cars).orElse(0);
		List<String> winners = getWinnersByMaxMoveCount(cars, maxMoveCount);
		System.out.println("최종 우승자 : " + String.join(", ", winners));
	}

	private OptionalInt getMaxMoveCount(List<Car> cars) {
		return cars.stream()
				.mapToInt(Car::getMoveCount)
				.max();
	}

	private List<String> getWinnersByMaxMoveCount(List<Car> cars, int maxMoveCount) {
		return cars.stream()
				.filter(car -> car.getMoveCount() == maxMoveCount)
				.map(Car::getName)
				.toList();
	}
}
