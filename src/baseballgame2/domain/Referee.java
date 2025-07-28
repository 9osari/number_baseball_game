package baseballgame2.domain;

import baseballgame2.config.GameSetting;

import java.util.List;
import java.util.Objects;

/**
 * Referee는 값을 비교하는 역할
 * answer(문제) 와 playerInput(입력값)을 가져와 값을 비교해
 * 결과를 Score<T>로 반환
 */
public class Referee<T> {
    private final GameSetting<T> setting;

    public Referee(GameSetting<T> setting) {
        this.setting = setting;
    }

    public Score<T> judge(List<T> answer, List<T> playerInput) {
        Score<T> score = new Score<>(0, 0, setting);

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
