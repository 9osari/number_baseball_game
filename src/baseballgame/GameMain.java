package baseballgame;

import java.util.Arrays;
import java.util.Scanner;

public class GameMain {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the game!");

        boolean playAgein = true;
        while (playAgein) {
            playGame();
            playAgein = askForRestart();
        }
    }

    private static boolean askForRestart() {
        while (true) {
            System.out.println("Do you want to play again? (1 -> reStart or 2 -> End)");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    return true;
                } else if (choice == 2) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice. choose 1 or 2.");
            }
        }
    }

    private static void playGame() {
        MakeNumber makeNumber = new MakeNumber();
        makeNumber.makeAnswerNumber();
        InputNumber inputNumber = new InputNumber();
        CheckNumber checkNumber = new CheckNumber(makeNumber.getNumber());

        System.out.println("Debug - Answer: " + Arrays.toString(makeNumber.getNumber()));
        do {
            inputNumber.inputNumber();  //사용자 값 입력 && 유효성검사
        } while (!checkGameEnd(checkNumber, inputNumber));
    }

    private static boolean checkGameEnd(CheckNumber checkNumber, InputNumber inputNumber) {
        if (checkNumber.matchNumber(inputNumber.getNumber())) {
            System.out.println("Game End");
            return true;
        }
        return false;
    }
}
