package baseballgame2;

import baseballgame2.config.GameSetting;
import baseballgame2.setting.AlphabetGameSetting;
import baseballgame2.setting.BaseBallGameSetting;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * 게임 시작점.
 * 1-> 숫자야구, 2->알파벳맞추기? 는 피드백 받고 ㄱㄱㄱ
 */
public class Main {
    public static void main(String[] args) {
        //숫자야구
        GameSetting<Integer> setting = new BaseBallGameSetting();
        Supplier<Integer> valueGenerator = () -> (int) (Math.random() * 9 + 1);
        Function<String, Integer> parser = Integer::parseInt;

        //알파벳 맞추기
        //GameSetting setting = new AlpabetGameSetting();
        //Supplier<Character> valueGenerator = () -> (char) (Math.random() * 26 + 'a');
        //Function<String, Character> parser = s -> s.charAt(0);

        GameMain<Integer> gameMain = new GameMain<>(setting, valueGenerator, parser);
        gameMain.start();
    }
}