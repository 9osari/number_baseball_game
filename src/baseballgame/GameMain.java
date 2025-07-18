package baseballgame;

import java.util.Arrays;

public class GameMain {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber(makeNumber()); //정답을 checkNumber로 보냄
        InputNumber inputNumber = new InputNumber();

        do {
            inputNumber.inputNumber();  //사용자 값 입력 && 유효성검사
        } while (!checkGameEnd(checkNumber, inputNumber));
    }

    private static boolean checkGameEnd(CheckNumber checkNumber, InputNumber inputNumber) {
        if(checkNumber.matchNumber(inputNumber.getNumber())) {
            System.out.println("Game End");
            /**
             * 종료 후 1을 입력하면 게임을 재시작 2를 입력하면 프로그램 종료 만들어야함.
             */
            return true;
        }
        return false;
    }

    public static int[] makeNumber() {
        MakeNumber makeNumber = new MakeNumber();
        makeNumber.makeAnswerNumber();
        int[] answer = makeNumber.getNumber();
        System.out.println("Deburr : " + Arrays.toString(answer) + "\n");
        return answer;
    }
}
