package baseballgame2.config;

import baseballgame2.domain.AnswerGenerator;
import baseballgame2.Game;
import baseballgame2.controller.GameController;
import baseballgame2.domain.Player;
import baseballgame2.domain.Referee;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 게임실행에 필요한 객체를 조립해 GameController 생성
 */
public class GameConfig<T> {
    private final GameSetting<T> setting;
    private final AnswerGenerator<T> generator;
    private final Player<T> player;
    private final Referee<T> referee;
    private final Game game;

    public GameConfig(GameSetting<T> setting, Supplier<T> supplier, Function<String, T> parser) {
        this.setting = setting;
        this.generator = new AnswerGenerator<>(setting, supplier);
        this.player = new Player<>(setting, parser);
        this.referee = new Referee<>(setting);
        this.game = new GameController<>(player, referee, generator);
    }
    public Game getGame() {
        return game;
    }
}
