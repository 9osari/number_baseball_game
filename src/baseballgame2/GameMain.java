package baseballgame2;

import baseballgame2.config.GameConfig;
import baseballgame2.config.GameSetting;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 게임을 구성하고 시작하는 클래스
 * 문자,숫자 등 게임 모드?를 추상화...
 */
public class GameMain<T> {
    private final GameSetting<T> setting;
    private final Supplier<T> supplier;
    private final Function<String, T> parser;

    public GameMain(GameSetting<T> setting, Supplier<T> supplier, Function<String, T> parser) {
        this.setting = setting;
        this.supplier = supplier;
        this.parser = parser;
    }

    public void start() {
        new GameConfig<>(setting, supplier, parser)
                .getGame()
                .start();
    }

}
