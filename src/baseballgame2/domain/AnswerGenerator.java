package baseballgame2.domain;

import baseballgame2.config.GameSetting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * AnswerGenerator는 정답생성만 책임.
 */
public class AnswerGenerator<T> {
    private final GameSetting<T> setting;
    private final Supplier<T> RandomElement;

    public AnswerGenerator(GameSetting<T> setting, Supplier<T> randomElement) {
        this.setting = setting;
        this.RandomElement = randomElement;
    }

    public List<T> generate() {
        //증복X HashSet 사용
        Set<T> elements = new HashSet<>();
        /*Random random = new Random();*/
        while (elements.size() < setting.getAnswerLength()) {
            T element = RandomElement.get(); //무작위 요소 생성
            if(setting.isValidElement(element)) {
                elements.add(element);
            }
        }
        return new ArrayList<>(elements);
    }
}
