package baseballgame2.config;

/**
 * 게임 규칙 설정을 위한 인터페이스
 */
public interface GameSetting<T> {
    int getAnswerLength(); //정답 길이

    int getMaxNumber(); //최대 숫자

    boolean isValidElement(T element); //범위
}
