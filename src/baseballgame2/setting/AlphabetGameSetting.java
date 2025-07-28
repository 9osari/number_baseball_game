package baseballgame2.setting;

import baseballgame2.config.GameSetting;

/**
 * GameSetting<Character> 구현체
 * 알파벳게임의 규칙을 정의하는 설정 객체
 * 정답길이, 문자범위 등 게임에 필요한 설정값 제공.
 */
public class AlphabetGameSetting implements GameSetting<Character> {

    @Override
    public int getAnswerLength() {
        return 3;
    }

    @Override
    public int getMaxNumber() {
        return 26;
    }

    //문자범위 지정
    @Override
    public boolean isValidElement(Character element) {
        return element >= 'a' && element <= 'z';
    }
}
