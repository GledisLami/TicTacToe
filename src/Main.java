import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String x = "x";
        String o = "o";
        int input;
        do {
            int[][] positions = {{1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}};
            String[][] stringPositions = {{"*", "*", "*"},
                    {"*", "*", "*"},
                    {"*", "*", "*"}};
            boolean[][] booleanPositions = {{false, false, false},
                    {false, false, false},
                    {false, false, false}};
            System.out.println("Tic Tac Toe\nChoose a game mode:\n1.Vs AI\n2.Vs Player\n3.Exit");
            input = 0;
            input = exception(sc, input);
            switch (input) {
                case 1: {
                    System.out.println("Choose which will you play x/o\n1.x\n2.o");
                    int inputxo = 0;
                    inputxo = exception(sc, inputxo);
                    switch (inputxo) {
                        case 1: {
                            startingGrid();
                            gameLogicYAI(sc, x, o, positions, stringPositions, booleanPositions);
                            System.out.println();
                            break;
                        }
                        case 2: {
                            startingGrid();
                            gameLogicXAI(sc, x, o, positions, stringPositions, booleanPositions);
                            System.out.println();
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    startingGrid();
                    gameLogic(sc, x, o, positions, stringPositions, booleanPositions);
                    System.out.println();
                    break;
                }
                case 3: {
                    System.out.println("Goodbye!");
                    break;
                }
            }
        } while (input != 3);
    }

    private static void startingGrid() {
        System.out.println("1|2|3");
        System.out.println("4|5|6");
        System.out.println("7|8|9");
        System.out.println();
    }

    private static void gameLogic(Scanner sc, String x, String o, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        do {
            xLogic(sc, x, positions, stringPositions, booleanPositions);
            printGame(stringPositions);
            if (gameOverX(stringPositions) || (draw(booleanPositions))) {
                break;
            }
            yLogic(sc, o, positions, stringPositions, booleanPositions);
            printGame(stringPositions);
            if (gameOverY(stringPositions) || (draw(booleanPositions))) {
                break;
            }
        } while (!gameOverX(stringPositions) || !gameOverY(stringPositions));
    }

    private static void gameLogicXAI(Scanner sc, String x, String o, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        do {
            xAiLogic(x, positions, stringPositions, booleanPositions);
            printGame(stringPositions);
            System.out.println();
            if (gameOverX(stringPositions) || (draw(booleanPositions))) {
                break;
            }
            yLogic(sc, o, positions, stringPositions, booleanPositions);
            printGame(stringPositions);
            System.out.println();
            if (gameOverY(stringPositions) || (draw(booleanPositions))) {
                break;
            }
        } while (!gameOverX(stringPositions) || !gameOverY(stringPositions));
    }

    private static void gameLogicYAI(Scanner sc, String x, String o, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        do {
            xLogic(sc, x, positions, stringPositions, booleanPositions);
            printGame(stringPositions);
            System.out.println();
            if (gameOverX(stringPositions) || (draw(booleanPositions))) {
                break;
            }
            yAiLogic(o, positions, stringPositions, booleanPositions);
            printGame(stringPositions);
            System.out.println();
            if (gameOverY(stringPositions) || (draw(booleanPositions))) {
                break;
            }
        } while (!gameOverX(stringPositions) || !gameOverY(stringPositions));
    }

    private static void yLogic(Scanner sc, String o, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        boolean repeatDoWhileO;
        do {
            repeatDoWhileO = true;
            System.out.println("Enter a position from 1-9 for o");
            int m = 0;
            m = exception(sc, m);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (m == positions[i][j]) {
                        if (!booleanPositions[i][j]) {
                            booleanPositions[i][j] = true;
                            repeatDoWhileO = false;
                            stringPositions[i][j] = o;
                            break;
                        }
                        System.out.println("Not valid or occupied space");
                    }
                }
            }
        } while (repeatDoWhileO);
    }

    private static void xLogic(Scanner sc, String x, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        boolean repeatDoWhileX;
        do {
            repeatDoWhileX = true;
            System.out.println("Enter a position from 1-9 for x");
            int n = 0;
            n = exception(sc, n);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (n == positions[i][j]) {
                        if (!booleanPositions[i][j]) {
                            booleanPositions[i][j] = true;
                            repeatDoWhileX = false;
                            stringPositions[i][j] = x;
                            break;
                        }
                        System.out.println("Not valid or occupied space");
                    }
                }
            }
        } while (repeatDoWhileX);
    }

    private static void xAiLogic(String x, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        boolean repeatDoWhileX;
        Random rand = new Random();
        do {
            repeatDoWhileX = true;
            int n = rand.nextInt(9) + 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (n == positions[i][j]) {
                        if (!booleanPositions[i][j]) {
                            booleanPositions[i][j] = true;
                            repeatDoWhileX = false;
                            stringPositions[i][j] = x;
                            break;
                        }
                    }
                }
            }
        } while (repeatDoWhileX);
    }

    private static void yAiLogic(String x, int[][] positions, String[][] stringPositions, boolean[][] booleanPositions) {
        boolean repeatDoWhileY;
        Random rand = new Random();
        do {
            repeatDoWhileY = true;
            int m = rand.nextInt(9) + 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (m == positions[i][j]) {
                        if (!booleanPositions[i][j]) {
                            booleanPositions[i][j] = true;
                            repeatDoWhileY = false;
                            stringPositions[i][j] = x;
                            break;
                        }
                    }
                }
            }
        } while (repeatDoWhileY);
    }

    public static boolean gameOverX(String[][] arr) {
        //for x
        if ((arr[0][0].equals("x") && arr[0][1].equals("x") && arr[0][2].equals("x")) || (arr[1][0].equals("x") && arr[1][1].equals("x") && arr[1][2].equals("x")) || (arr[2][0].equals("x") && arr[2][1].equals("x") && arr[2][2].equals("x")) || (arr[0][0].equals("x") && arr[1][1].equals("x") && arr[2][2].equals("x")) || (arr[0][0].equals("x") && arr[1][0].equals("x") && arr[2][0].equals("x")) || (arr[0][1].equals("x") && arr[1][1].equals("x") && arr[2][1].equals("x")) || (arr[0][2].equals("x") && arr[1][2].equals("x") && arr[2][2].equals("x")) || (arr[2][0].equals("x") && arr[1][1].equals("x") && arr[0][2].equals("x"))) {
            System.out.println("Game over\nx won");
            return true;
        }
        return false;
    }

    public static boolean gameOverY(String[][] arr) {
        if ((arr[0][0].equals("o") && arr[0][1].equals("o") && arr[0][2].equals("o")) || (arr[1][0].equals("o") && arr[1][1].equals("o") && arr[1][2].equals("o")) || (arr[2][0].equals("o") && arr[2][1].equals("o") && arr[2][2].equals("o")) || (arr[0][0].equals("o") && arr[1][1].equals("o") && arr[2][2].equals("o")) || (arr[0][0].equals("o") && arr[1][0].equals("o") && arr[2][0].equals("o")) || (arr[0][1].equals("o") && arr[1][1].equals("o") && arr[2][1].equals("o")) || (arr[0][2].equals("o") && arr[1][2].equals("o") && arr[2][2].equals("o")) || (arr[2][0].equals("o") && arr[1][1].equals("o") && arr[0][2].equals("o"))) {
            System.out.println("Game over\no won");
            return true;
        }
        return false;
    }

    public static boolean draw(boolean[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!arr[i][j]) {
                    return false;
                }
            }
        }
        System.out.println("Draw");
        return true;
    }

    public static void printGame(String[][] arr) {
        System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr[0][2]);
        System.out.println(arr[1][0] + " " + arr[1][1] + " " + arr[1][2]);
        System.out.println(arr[2][0] + " " + arr[2][1] + " " + arr[2][2]);
    }

    public static int exception(Scanner sc, int input){
        boolean Error = false;
        while (!Error){
            try {
                input = sc.nextInt();
                Error=true;

            } catch (InputMismatchException ime){
                System.out.println("You did not enter an integer, please enter an integer value");
                sc.nextLine();// this consumes the invalid token
            }
        }
        return input;
    }
}