package baseballgame2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final GameSetting setting;

    public Player(GameSetting setting) {
        this.setting = setting;
    }

    Scanner sc = new Scanner(System.in);
    public List<Integer> input() {
        ArrayList<Integer> list = new ArrayList<>();

        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            String num = sc.nextLine();

            //유효성검사 - 자릿수 확인
            if (num.length() != setting.getAnswerLength()) {
                System.out.println("숫자를 확인해주세요");
                continue;
            }

            //유효성검사 - 숫자형식 확인
            for (int i = 0; i < setting.getAnswerLength(); i++) {
                char c = num.charAt(i);
                if (!Character.isDigit(c)) throw new IllegalArgumentException("숫자 형식을 확인");
            }

            //값 저장
            for (int i = 0; i < setting.getAnswerLength(); i++) {
                int digit = Character.getNumericValue(num.charAt(i));
                list.add(digit);
            }
            break;
        }
        return list;
    }

}
