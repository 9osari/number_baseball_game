package baseballgame;

import java.util.Scanner;

public class InputNumber {
    private final int[] number;
    private final int CAPACITY = 3;
    Scanner sc = new Scanner(System.in);

    public InputNumber() {
        this.number = new int[CAPACITY];
    }

    public int[] getNumber() {
        return number;
    }

    public void inputNumber() {
        while (true) {
            System.out.print("Input Number : ");
            String num = sc.nextLine();

            //유효성검사 - 자릿수 확인
            if (num.length() != CAPACITY) {
                System.out.println("Check Number.");
                continue;
            }

            //유효성검사 - 숫자형식 확인
            for (int i = 0; i < CAPACITY; i++) {
                char c = num.charAt(i);
                if (!Character.isDigit(c)) throw new IllegalArgumentException("Input Number is invalid.");
            }

            //값 저장
            for (int i = 0; i < CAPACITY; i++) {
                number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
            }

            
            break;
        }
    }
}
