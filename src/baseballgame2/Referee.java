package baseballgame2;

import java.util.List;
import java.util.Objects;

/**
 * Referee는 값을 비교하는 역할
 * answer(문제) 와 playerInput(입력값)을 가져와 값을 비교 해
 * 결과를 Score로 반환
 */
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
