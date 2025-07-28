package baseballgame2.controller;

import baseballgame2.domain.AnswerGenerator;
import baseballgame2.Game;
import baseballgame2.domain.Player;
import baseballgame2.domain.Referee;
import baseballgame2.domain.Score;

import java.util.List;
import java.util.Scanner;

/**
 * GameController는 게임의 흐름만 책임.
 * 게임을 시작, 재시작 묻기 등
 */
public class GameController<T> implements Game {
    Scanner sc = new Scanner(System.in);
    private final Player<T> player;
    private final Referee<T> referee;
    private final AnswerGenerator<T> answerGenerator;

    public GameController(Player<T> player, Referee<T> referee, AnswerGenerator<T> answerGenerator) {
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
        List<T> answer = answerGenerator.generate();
        System.out.println("디버깅용: " + answer);
        //아웃될때까지 반복
        while(true) {
            //사용자 입력
            List<T> playerInput = player.input();

            //정답과 입력값 비교하여 판정
            Score<T> score = referee.judge(answer, playerInput);

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
