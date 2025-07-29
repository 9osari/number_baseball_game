package baseballgame2;

import baseballgame2.config.GameSetting;
import baseballgame2.controller.GameController;
import baseballgame2.domain.AnswerGenerator;
import baseballgame2.domain.Player;
import baseballgame2.domain.Referee;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 게임 실행에 필요한 구성 요소들을 조립하고, 게임을 시작하는 책임
 * GameSetting, AnswerGenerator, Player, Referee를 생성해 GameController에 전달
 */
public class GameMain<T> {
    private final GameSetting<T> setting;
    private final AnswerGenerator<T> generator;
    private final Player<T> player;
    private final Referee<T> referee;
    private final Game game;

    public GameMain(GameSetting<T> setting, Supplier<T> supplier, Function<String, T> parser) {
        this.setting = setting;
        this.generator = new AnswerGenerator<>(setting, supplier);
        this.player = new Player<>(setting, parser);
        this.referee = new Referee<>(setting);
        this.game = new GameController<>(player, referee, generator);
    }

    public void start() {
        game.start();
    }
}
