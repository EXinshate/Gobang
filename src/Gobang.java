import java.util.Random;
import java.util.Scanner;

/**
 * 五子棋游戏类
 */
public class Gobang {
    // 棋盘大小常量
    private static final int BOARD_SIZE = 15;
    // 棋盘数组
    private String[][] board;
    // 当前玩家，初始为玩家符号
    private String currentPlayer;
    // 玩家符号
    private String playerSymbol = "●";
    // 电脑符号
    private String computerSymbol = "○";

    /**
     * 构造函数，初始化棋盘和当前玩家
     */
    public Gobang() {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = playerSymbol; // 玩家先手
        initBoard();
    }

    /**
     * 初始化棋盘为空状态
     */
    public void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "+";
            }
        }
    }

    /**
     * 打印当前棋盘状态
     */
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 检查指定位置是否为有效落子位置
     *
     * @param row 行坐标
     * @param col 列坐标
     * @return 如果位置有效返回true，否则返回false
     */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col].equals("+");
    }

    /**
     * 玩家落子
     */
    public void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("请输入你的下棋位置 (行 列): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!isValidMove(row, col));
        board[row][col] = playerSymbol;
    }

    /**
     * 电脑随机落子
     */
    public void computerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
        } while (!isValidMove(row, col));
        board[row][col] = computerSymbol;
        System.out.println("电脑下棋位置: " + row + " " + col);
    }

    /**
     * 检查指定符号是否获胜
     *
     * @param symbol 棋子符号
     * @return 如果获胜返回true，否则返回false
     */
    public boolean checkWin(String symbol) {
        // 检查行、列和对角线
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].equals(symbol)) {
                    if (checkDirection(i, j, 1, 0, symbol) || // 横向
                            checkDirection(i, j, 0, 1, symbol) || // 纵向
                            checkDirection(i, j, 1, 1, symbol) || // 斜向 \
                            checkDirection(i, j, 1, -1, symbol)) { // 斜向 /
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查指定方向上是否连续有五个相同符号
     *
     * @param row 行坐标
     * @param col 列坐标
     * @param deltaRow 行方向增量
     * @param deltaCol 列方向增量
     * @param symbol 棋子符号
     * @return 如果连续五个相同符号返回true，否则返回false
     */
    private boolean checkDirection(int row, int col, int deltaRow, int deltaCol, String symbol) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            int newRow = row + i * deltaRow;
            int newCol = col + i * deltaCol;
            if (newRow >= 0 && newRow < BOARD_SIZE && newCol >= 0 && newCol < BOARD_SIZE && board[newRow][newCol].equals(symbol)) {
                count++;
            } else {
                break;
            }
        }
        return count == 5;
    }

    /**
     * 开始游戏，轮流下子直到有玩家获胜或棋盘填满
     */
    public void play() {
        while (true) {
            printBoard();
            playerMove();
            if (checkWin(playerSymbol)) {
                printBoard();
                System.out.println("恭喜你，赢了！");
                break;
            }
            computerMove();
            if (checkWin(computerSymbol)) {
                printBoard();
                System.out.println("电脑赢了！");
                break;
            }
        }
    }

    /**
     * 游戏入口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Gobang game = new Gobang();
        game.play();
    }
}
