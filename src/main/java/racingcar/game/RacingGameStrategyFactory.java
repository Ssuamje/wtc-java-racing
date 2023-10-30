package racingcar.game;

import camp.nextstep.edu.missionutils.Randoms;

public class RacingGameStrategyFactory {

	public static RacingGameStrategy createRandomsStrategy(int min, int max, int threshold) {
		if (min < 0 || max < 0 || threshold < 0) {
			throw new IllegalArgumentException("범위 값과 경계 값은 음수일 수 없습니다.");
		}
		if (min > max) {
			throw new IllegalArgumentException("최소값은 최대값보다 클 수 없습니다.");
		}
		if (threshold > max) {
			throw new IllegalArgumentException("경계 값은 최대값보다 클 수 없습니다.");
		}
		return () -> Randoms.pickNumberInRange(min, max) >= threshold;
	}
}
