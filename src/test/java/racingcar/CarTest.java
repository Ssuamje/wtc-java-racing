package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.game.Car;
import racingcar.game.ExceptionStatus;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarTest {
	@Nested
	@DisplayName("자동차를 생성할 때,")
	class CarConstructionTest {
		private final String empty = "";
		private final String longName = createRandomString(6);
		private final String blankName = "   ";

		private final String properName = createRandomString(5);
		@Test
		@DisplayName("이름이 5자를 초과하면 안 된다.")
		void 이름_5자_초과() throws Exception {
			assertThatThrownBy(() -> new Car(longName, 0))
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining(ExceptionStatus.INVALID_CAR.getMessage());
		}

		@DisplayName("이름이 빈 문자열이면 안 된다.")
		@Test
		void 이름_빈_문자열() {
			assertThatThrownBy(() -> new Car(empty, 0))
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining(ExceptionStatus.INVALID_CAR.getMessage());
		}

		@DisplayName("이름이 공백이면 안 된다.")
		@Test
		void 이름_공백() {
			assertThatThrownBy(() -> new Car(blankName, 0))
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining(ExceptionStatus.INVALID_CAR.getMessage());
		}

		@DisplayName("1자 ~ 5자 사이의 문자열이면 생성에 성공한다.")
		@Test
		void 이름_정상() {
			assertThat(new Car(properName, 0)).isNotNull();
		}

	}
	private String createRandomString(int length) {
		return UUID.randomUUID().toString().substring(0, length);
	}
}
