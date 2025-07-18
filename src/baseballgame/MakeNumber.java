package baseballgame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MakeNumber {
    private final int[] number;
    private final int CAPACITY = 3;

    public MakeNumber() {
        this.number = new int[CAPACITY];
    }

    public int[] getNumber() {
        return number;
    }

    //3자리 랜덤숫자 만들기
    public void makeAnswerNumber() {
        //증복X HashSet 사용
        Set<Integer> numValid = new HashSet<>();
        Random random = new Random();
        int index = 0;
        while (numValid.size() < CAPACITY) {
            int num = random.nextInt(9) + 1;
            if(numValid.add(num)) number[index++] = num;
        }
    }
}
