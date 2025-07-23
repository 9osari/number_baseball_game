package baseballgame2;

/**
 * Score는 Referee의 판정 결과를 담는 객체
 * strike, ball 를 저장하고 관련 기능 제공
 */
public class Score {
    private final GameSetting setting;
    private int strike;
    private int ball;

    public Score(int strike, int ball, GameSetting setting) {
        this.strike = strike;
        this.ball = ball;
        this.setting = setting;
    }

    public void addStrike() {
        strike++;
    }
    public void addBall() {
        ball++;
    }

    public boolean isThreeStrike() {
        return strike == setting.getAnswerLength();
    }

    @Override
    public String toString() {
        if(strike == 0 && ball == 0) {
            return "Noting";
        }
        return String.format("Strike: %d, Ball: %d", strike, ball);
    }
}
