package baseballgame2.setting;

import baseballgame2.config.GameSetting;

/**
 * GameSetting<Integer> 구현체
 * 야구게임의 규칙을 정의하는 설정 객체
 * 정답길이, 숫자범위 등 게임에 필요한 설정값 제공.
 */
public class BaseBallGameSetting implements GameSetting<Integer> {
    @Override
    public int getAnswerLength() {
        return 3;
    }

    @Override
    public int getMaxNumber() {
        return 9;
    }

    //숫자범위 지정
    @Override
    public boolean isValidElement(Integer element) {
        return element >= 1 && element <= getMaxNumber();
    }
}
