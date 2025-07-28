package baseballgame2.domain;

import baseballgame2.config.GameSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * 플레이어는 3개의 값 입력가능
 * 중복된 값 입력불가
 * 범위는 게임설정에 따라..
 * 잘못된 값 입력 -> IllegalArgumentException
 */
public class Player<T> {
    private final GameSetting<T> setting;
    private final Function<String, T> parser;

    public Player(GameSetting<T> setting, Function<String, T> parser) {
        this.setting = setting;
        this.parser = parser;
    }

    Scanner sc = new Scanner(System.in);
    public List<T> input() {
        while (true) {
            System.out.print("값을 입력해주세요 : ");
            String num = sc.nextLine();

            //유효성검사 - 자릿수 확인
            if (num.length() != setting.getAnswerLength()) {
                System.out.println("입력한 값을 확인해주세요");
                continue;
            }

            //유효성검사 - 숫자형식 확인
            //이거 어캐하지ㅋㅋ
            /*for (int i = 0; i < setting.getAnswerLength(); i++) {
                char c = num.charAt(i);
                if (!Character.isDigit(c)) throw new IllegalArgumentException("숫자 형식을 확인");
            }*/

            List<T> list = new ArrayList<>();
            for (int i = 0; i < num.length(); i++) {
                list.add(parser.apply(String.valueOf(num.charAt(i))));
            }
            return list;
        }
    }
}
