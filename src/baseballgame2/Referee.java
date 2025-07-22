package baseballgame2;

import java.util.List;
import java.util.Objects;

public class Referee {
    private final GameSetting setting;

    public Referee(GameSetting setting) {
        this.setting = setting;
    }

    public Score judge(List<Integer> answer, List<Integer> playerInput) {
        Score score = new Score(0, 0, setting);

        for(int i = 0; i < setting.getAnswerLength(); i++) {
            for(int j = 0; j < setting.getAnswerLength(); j++) {
                if(Objects.equals(answer.get(i), playerInput.get(j))) {
                    if(i == j) score.addStrike();
                    else score.addBall();
                }
            }
        }
        return score;
    }
}
