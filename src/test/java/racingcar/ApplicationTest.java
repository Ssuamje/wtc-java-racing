package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.game.Car;
import racingcar.game.ExceptionStatus;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    /*-------------------------- 추가 테스트 --------------------------*/
    @DisplayName("자동차를 생성할 때 이름이 5자를 초과하거나 빈 문자열이면 예외가 발생한다.")
    @Test
    void invalid_car_name() {
        //given
        String empty = "";
        String longName = "안녕하세요반갑습니다";

        //when, then
        assertThatThrownBy(() -> new Car(empty, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionStatus.INVALID_CAR.getMessage());

        assertThatThrownBy(() -> new Car(longName, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionStatus.INVALID_CAR.getMessage());
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
