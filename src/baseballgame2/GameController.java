package baseballgame2;

import java.util.List;
import java.util.Scanner;

/**
 * GameController는 게임의 흐름만 책임.
 * 게임을 시작, 재시작 묻기 등
 */
public class GameController implements Game {
    Scanner sc = new Scanner(System.in);
    private final Player player;
    private final Referee referee;
    private final AnswerGenerator answerGenerator;

    public GameController(Player player, Referee referee, AnswerGenerator answerGenerator) {
        this.player = player;
        this.referee = referee;
        this.answerGenerator = answerGenerator;
    }

    @Override
    public void start() {
        do {
            play();
        } while (wantRestart());
    }

    private void play() {
        //정답생성
        List<Integer> answer = answerGenerator.generate();
        System.out.println("디버깅용: " + answer);
        //아웃될때까지 반복
        while(true) {
            //사용자 입력
            List<Integer> playerInput = player.input();

            //정답과 입력값 비교
            Score score = referee.judge(answer, playerInput);

            System.out.println(score);
            if(score.isThreeStrike()) {
                System.out.println("3 스트라이크 게임종료");
                break;
            }
        }
    }

    private boolean wantRestart() {
        while (true) {
            System.out.println("게임 재시작? (1 -> 재시작 or 2 -> 종료)");
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
                System.out.println("1 또는 2를 입력해주세요.");
            }
        }
    }
}
