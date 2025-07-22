package baseballgame2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * AnswerGenerator는 정답생성만 책임.
 * 1~9 로 이루어진 3자리의 수 생성
 * 종복된 수 불가
 */
public class AnswerGenerator {
    private final GameSetting setting;

    public AnswerGenerator(GameSetting setting) {
        this.setting = setting;
    }

    public List<Integer> generate() {
        //증복X HashSet 사용
        Set<Integer> elements = new HashSet<>();
        Random random = new Random();
        while (setting.getAnswerLength() > elements.size()) {
            int num = random.nextInt(9) + 1;
            elements.add(num);
        }
        return new ArrayList<>(elements);
    }
}
