package baseballgame2;

/**
 * 야구게임의 규칙을 정의하는 설정 객체
 * 정답길이, 숫자범위 등 게임에 필요한 설정값 제공.
 */
public class BaseBallGameSetting implements GameSetting {
    @Override
    public int getAnswerLength() {
        return 3;
    }

    @Override
    public int getMaxNumber() {
        return 9;
    }
}
