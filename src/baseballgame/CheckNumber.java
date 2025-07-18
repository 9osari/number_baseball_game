package baseballgame;

public class CheckNumber {
    private final int[] answer;
    private final int CAPACITY = 3;

    public CheckNumber(int [] answer) {
        this.answer = answer;
    }

    public boolean matchNumber(int [] input) {
        int strike = 0;
        int ball = 0;

        for(int i = 0; i < CAPACITY; i++) {
            for(int j = 0; j < CAPACITY; j++) {
                if(answer[i] == input[j]) {
                    if(i == j) strike++;
                    else ball++;
                }
            }
        }

        if(strike == 3) {
            System.out.println("3 Strike");
            return true;
        } else if(strike == 0 && ball == 0) {
            System.out.println("Noting");
        } else {
            System.out.printf("%d Strike %d Ball\n", strike, ball);
        }
        return false;
    }
}
