# 나는 한다 야구게임

## 요구사항

- 1 ~ 9 중 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임
- 플레이어는 3자리의 수를 입력 가능
- 플레이어는 중복된 수를 입력 가능
- 입력한 숫자와 정답 간 일치하는 숫자가 있으면 볼이나 스트라이크
- 일치하는 숫자의 위치가 같으면 스트라이크
- 일치하는 숫자의 위치가 다르면 볼
- 일치하는 숫자가 하나도 없다면 낫싱
- 3스트라이크라면 아웃
- 아웃시킬 때까지 반복하고 아웃시키면 게임 종료
- 종료 후 1을 입력하면 게임을 재시작하고 2를 입력하면 프로그램 종료
- 잘못된 값을 입력하면 IllegalArgumentException 예외가 발생

## 최초 구현

2025-07-18

- 숫자 만들기
    
    ```java
    public class MakeNumber {
        private final int[] number;
        private final int CAPACITY = 3;
    
        public MakeNumber() {
            this.number = new int[CAPACITY];
        }
    
        public int[] getNumber() {
            return number;
        }
    
        //3자리 숫자 만들기
        public void makeNumber() {
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
    ```
    
- 숫자 입력받기 & 예외처리
    
    ```java
    public class InputNumber {
        private final int[] number;
        private final int CAPACITY = 3;
    
        //Scanner로 입력 받고, 유효성 검사
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
    
                //유효성검사: 자릿수 확인
                if (num.length() != CAPACITY) {
                    System.out.println("Check Number.");
                    continue;
                }
    
                //유효성검사: 숫자인지 확인
                for (int i = 0; i < CAPACITY; i++) {
                    char c = num.charAt(i);
                    if (!Character.isDigit(c)) throw new IllegalArgumentException("Input Number is invalid.");
                }
    
                //값 넣기
                for (int i = 0; i < CAPACITY; i++) {
                    number[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
                }
                
                break;
            }
        }
    }
    ```
    
- 숫자 매칭시켜 S,B,N 판단
    
    ```java
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
    ```
    
- 게임 재시작 묻기
    
    ```java
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
    ```
    
- 예외처리
    
    ```java
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
    ```
    

---

## 자체 피드백 (2025-07-21)

애초에 구상 자체를 절차지향으로 생각해서 절차지향적 코드가 나오는거 같음.. (기능중심 사고?)

어떤 기능이 있지?

이 기능을 언제 실행하지?

이렇게 생각 말고,

이 프로그램의 등장인물(객체)은 누구인지

각 객체는 어떤 역할을 가져야 하는지?

서로 어떻게 메세지를 주고받을지 ← ???

한 객체가 많은 책임을 가지진 않는지

## 피드백 받은 후

1. 메세지 설계
2. 메세지가 각각 하나의 메서드로 매치
3. 비슷한 성격의 메세지끼리 모아 하나의 책임으로 묶음
4. 해당 책임을 가질 객체의 이름을 추상적으로 지어 만든다 → 역할 생성

메세지를 만들면 책임이 생김 그게 역할
[눈으로 보는 OOP](https://www.notion.so/6bd16f7647554dd1bcca0001da50b617?pvs=21) ← 10번 읽기

---

## 피드백 받은 후 2.0 구현

- 설계 시작
    
    설계는 요구사항 분석 부터 시작!
    
- 구현 기능 파악
    
    ```java
    - Usecase- 1 ~ 9 중 서로 다른 수로 이루어진 3 자리의 수를 맞추는 게임
    - 플레이어는 3 자리의 수를 입력 가능
    - 플레이어는 중복된 수를 입력할 수 없음.
    - 입력한 숫자와 정답 간 일치하는 숫자가 있으면 볼이나 스트라이크
    - 일치하는 숫자의 위치가 같으면 스트라이크
    - 일치하는 숫자의 위치가 다르면 볼
    - 일치하는 숫자가 하나도 없다면 낫싱
    - 3 스트라이크라면 아웃
    - 아웃 시킬 때까지 반복하고 아웃 시키면 게임 종료
    - 종료 후 1을 입력하면 게임을 재 시작하고 2를 입력하면 프로그램 종료
    - 잘못된 값을 입력하면 IllegalArgumentException 예외가 발생
    ```
    
- 역할 분류
    
    1 ~ 9 중 서로 다른 수로 이루어진 3자리 수를 맞추는 게임!
    
    대강 비슷한 친구들끼리 모아보자
    
    - 게임의흐름? → `GameController`
        
        이 게임은 아웃할때까지 반복함
        
        게임종료(아웃) 후 사용자 입력 1 → 게임재시작, 2 → 종료
        
    - 플레이어 → `Player`
        
        플레이어는 1~9 중 서로 다른 수로 이루어진 3자리의 수 입력
        
        플레이어는 중복된 수를 입력 불가
        
        플레이어가 잘못된 값을 입력하면 IllegalArgumentException 예외가 발생
        
    - 정답 숫자 생성 → `AnswerGenerator`
        
        1~9 로 이루어진 3자리의 수 생성
        
        종복된 수 불가 
        
    - strike, Ball, Noting 판단 → `Referee`
        
        입력한 숫자 / 정답이 일치하면 S,B,N 판단
        
        3S면 아웃
        
    - strike, Ball, Noting 관리? → `Score`
        
        점수 즉 스트라이크, 볼 따로 관리하면 좋나?
        

각 객체의 이름은 그 책임을 도메인 모델 안에서 잘 표현해야 한다.

향후 **확장을 고려**해, 지나치게 구체적이기보단 **약간은 추상적으로** 짓자.

이제 협력에 필요한 메세지를 작성해보자..

---
    

## 제네릭 도입? (2025-07-28)

현재 숫자야구 ← `Integer` 로만 되어있음 나중에 도메인이 잘 나가서 알파벳게임도 추가된다면?

재사용성 증가와 유연한 API를 위해 제네릭을 도입하자..

- 제네릭 도입 후 메세지 설계
    ```mermaid
        classDiagram
        
        %% 인터페이스
        class GameSetting~T~ {
          <<interface>>
          +getAnswerLength(): int
          +getMaxNumber(): int
          +isValidElement(T): boolean
        }
        
        class Game {
          <<interface>>
          +start(): void
        }
        
        %% 구현체
        class BaseBallGameSetting {
          +getAnswerLength(): int
          +getMaxNumber(): int
          +isValidElement(Integer): boolean
        }
        
        class AlphabetGameSetting {
          +getAnswerLength(): int
          +getMaxNumber(): int
          +isValidElement(Character): boolean
        }
        
        %% 도메인 로직
        class AnswerGenerator~T~ {
          -setting: GameSetting~T~
          -randomElement: Supplier~T~
          +generate(): List~T~
        }
        
        class Player~T~ {
          -setting: GameSetting~T~
          -parser: Function~String, T~
          +input(): List~T~
        }
        
        class Referee~T~ {
          -setting: GameSetting~T~
          +judge(answer: List~T~, input: List~T~): Score~T~
        }
        
        class Score~T~ {
          -strike: int
          -ball: int
          -setting: GameSetting~T~
          +addStrike(): void
          +addBall(): void
          +isThreeStrike(): boolean
          +toString(): String
        }
        
        %% 실행 흐름
        class GameController~T~ {
          -player: Player~T~
          -referee: Referee~T~
          -generator: AnswerGenerator~T~
          +start(): void
        }
        
        class GameConfig~T~ {
          -setting: GameSetting~T~
          -generator: AnswerGenerator~T~
          -player: Player~T~
          -referee: Referee~T~
          -game: GameController~T~
          +getGame(): Game
        }
        
        class GameMain~T~ {
          -setting: GameSetting~T~
          -supplier: Supplier~T~
          -parser: Function~String, T~
          +start(): void
        }
        
        %% 관계 (타입 표기 없이 안정적으로 연결)
        GameMain --> GameConfig : 실행 시 조립
        GameConfig --> GameController
        GameController --> Player
        GameController --> Referee
        GameController --> AnswerGenerator
        GameController ..|> Game
        
        BaseBallGameSetting ..|> GameSetting
        AlphabetGameSetting ..|> GameSetting
        
        AnswerGenerator --> GameSetting
        Player --> GameSetting
        Referee --> GameSetting
        Score --> GameSetting
        Referee --> Score
    ```
<br>

| 파일                                           | 역할 요약                       |
| -------------------------------------------- | --------------------------- |
| `GameSetting<T>`                             | 게임 규칙 정의용 인터페이스             |
| `BaseBallGameSetting`, `AlphabetGameSetting` | 구체적인 게임 규칙 구현               |
| `GameConfig<T>`                              | 의존성 조립기 (GameController 구성) |
| `AnswerGenerator<T>`                         | 정답 생성기                      |
| `Player<T>`                                  | 사용자 입력 처리                   |
| `Referee<T>`                                 | 정답과 입력 비교 후 판정              |
| `Score<T>`                                   | 스트라이크/볼 상태 저장 및 출력          |
| `Game`                                       | 공통 실행 인터페이스                 |
