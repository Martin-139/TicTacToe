import java.util.Scanner;

public class TicTacToe {

    private char[][] gameBoard;
    private char turn;
    Scanner scanner;

    public static void main(String[] args) {
        TicTacToe main = new TicTacToe();
        main.initialize();
    }

    // Initialization
    public void initialize() {
        gameBoard = new char[3][3];
        turn = 'x';
        scanner = new Scanner(System.in);
        initBoard();
        printNumberBoard();
        playGame();
    }

    public void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = '_';
            }
        }
    }

    public void printNumberBoard() {
        int x = 1;
        System.out.println("Use numbers to put X and O on the board.");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(x + " ");
                x++;
            }
            System.out.println();
        }
        System.out.println("******************");
    }
    // ****************************************************************

    int response = 99;

    // GamePlay
    public void playGame() {
        drawBoard();
        System.out.println("Player " + turn + "'s turn");
        checkInputType();
    }

    public void checkInputType() {
        try {
            System.out.print("Position: ");
            response = scanner.nextInt();
            isAvailable();
        } catch (Exception e) {
            System.out.println("Wrong input type!");
            scanner.next();
            checkInputType();
        }
    }

    public void isAvailable() {
        if (response > 0 && response < 10) {
            char onPos = 'i';
            switch (response) {
            case 1:onPos = gameBoard[0][0];break;
            case 2:onPos = gameBoard[0][1];break;
            case 3:onPos = gameBoard[0][2];break;
            case 4:onPos = gameBoard[1][0];break;
            case 5:onPos = gameBoard[1][1];break;
            case 6:onPos = gameBoard[1][2];break;
            case 7:onPos = gameBoard[2][0];break;
            case 8:onPos = gameBoard[2][1];break;
            case 9:onPos = gameBoard[2][2];break;
            }
            if (onPos == '_') {
                updateBoard();
            } else {
                System.out.println("Chosen place is occupied");
                checkInputType();
            }
        } else {
            System.out.println("Use only number between 1 and 9");
            checkInputType();
        }
    }

    public void updateBoard() {
        switch (response) {
        case 1:gameBoard[0][0] = turn;break;
        case 2:gameBoard[0][1] = turn;break;
        case 3:gameBoard[0][2] = turn;break;
        case 4:gameBoard[1][0] = turn;break;
        case 5:gameBoard[1][1] = turn;break;
        case 6:gameBoard[1][2] = turn;break;
        case 7:gameBoard[2][0] = turn;break;
        case 8:gameBoard[2][1] = turn;break;
        case 9:gameBoard[2][2] = turn;break;
        }

        turn = turn == 'x' ? 'o' : 'x';

        switch (checkGameState()) {
        case 'x':
            System.out.println("Player X wins!");
            drawBoard();
            break;
        case 'o':
            System.out.println("Player O wins!");
            drawBoard();
            break;
        case 'd':
            System.out.println("Draw!");
            drawBoard();
            break;
        case 'n':
            playGame();
        }

    }

    // ****************************************************************

    // basic board
    public void drawBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    // ****************************************************************

    // checking result
    public char checkGameState() {
        if (checkDraw()) {
            return 'd';
        }
        return checkWinner();
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public char checkWinner() {
        for (int i = 1; i <= 8; i++) {
            String line = "";
            switch (i) {
            case 1:line = gameBoard[0][0] + "" + gameBoard[0][1] + "" + gameBoard[0][2];break;
            case 2:line = gameBoard[1][0] + "" + gameBoard[1][1] + "" + gameBoard[1][2];break;
            case 3:line = gameBoard[2][0] + "" + gameBoard[2][1] + "" + gameBoard[2][2];break;
            case 4:line = gameBoard[0][0] + "" + gameBoard[1][0] + "" + gameBoard[2][0];break;
            case 5:line = gameBoard[0][1] + "" + gameBoard[1][1] + "" + gameBoard[2][1];break;
            case 6:line = gameBoard[0][2] + "" + gameBoard[1][2] + "" + gameBoard[2][2];break;
            case 7:line = gameBoard[0][0] + "" + gameBoard[1][1] + "" + gameBoard[2][2];break;
            case 8:line = gameBoard[0][2] + "" + gameBoard[1][1] + "" + gameBoard[2][0];break;
            }
            if (line.equals("xxx")) {
                return 'x';
            } else if (line.equals("ooo")) {
                return 'o';
            }
        }
        return 'n';
    }
    // ****************************************************************

}