package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    // 커스텀 테스트 //
    // 정상 경우 테스트
    @Test
    @DisplayName("빈 값 입력 테스트")
    void test0() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    @DisplayName("정상 기본 구분자 입력 테스트")
    void test1() {
        assertSimpleTest(() -> {
            run("1,1:1");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    @DisplayName("정상 커스텀 구분자 입력 테스트")
    void test2() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("정상 커스텀 구분자 입력 테스트2")
    void test3() {
        assertSimpleTest(() -> {
            run("//.\\n1.2.3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    // 예외 처리 테스트
    @Test
    @DisplayName("숫자 입력이 아닌 경우 예외 (기본 구분자)")
    void test4() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("a,2:b"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("숫자 입력이 아닌 경우 예외 (커스텀 구분자)")
    void test5() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\na;2;b"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("양수 입력이 아닌 경우(음수 입력) 예외 (기본 구분자)")
    void test6() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2:3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("양수 입력이 아닌 경우(음수 입력) 예외 (커스텀 구분자)")
    void test7() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n-1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("기본 구분자 경우에서의 커스텀 구분자 임의 사용 예외")
    void test8() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1;2:3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("입력했던 커스텀 구분자가 아닌 다른 구분자 사용")
    void test9() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1'2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자 연속 등장 예외 (기본 구분자)")
    void test10() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,2:3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자 연속 등장 예외 (커스텀 구분자)")
    void test11() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자 위치 부정확으로 인한 예외 (기본 구분자 위치 앞)")
    void test12() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(":1:2:3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자 위치 부정확으로 인한 예외 (기본 구분자 위치 뒤)")
    void test13() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1:2:3:"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자 위치 부정확으로 인한 예외 (커스텀 구분자 위치 앞)")
    void test14() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자 위치 부정확으로 인한 예외 (커스텀 구분자 위치 뒤)")
    void test15() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;2;3;"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("띄어쓰기 구분자에 대하여 정상 처리함")
    void test16() {
        assertSimpleTest(() -> {
            run("// \\n1 2 3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("그러나, 띄어쓰기 구분자도 연속될 경우 예외")
    void test17() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("// \\n1  2    3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("int 범위 넘음 (한번에 입력된 숫자)")
    void test18() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("9000000000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("int 범위 넘음 (더한 숫자)")
    void test19() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1000000000:1200000000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
