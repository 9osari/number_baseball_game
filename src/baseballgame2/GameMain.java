package baseballgame2;

/**
 * 프로그램 실행의 시작 지점. GameController를 통해 게임을 시작
 */
public class GameMain {
    public static void main(String[] args) {
        GameSetting setting = new BaseBallGameSetting();

        AnswerGenerator answerGenerator = new AnswerGenerator(setting);
        Player player = new Player(setting);
        Referee referee = new Referee(setting);

        Game game = new GameController(player, referee, answerGenerator);
        game.start();
    }
}
