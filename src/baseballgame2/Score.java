package baseballgame2;

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
