import java.lang.reflect.Array;

public class Board {
    int n;
    int[][] field;

    int amountOfQueens;

    /**
     * Создает пустую доску
     */
    Board(int n) {
        this.n = n;
        this.field = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.field[i][j] = 0;
            }
        }
    }

    Board(int[][] b) {
        this.n = b.length;
        this.field = b;
    }

    // будет работать только на валидных
    public boolean isEnd() {
        int queens = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                queens += this.field[i][j];
            }
        }
        return queens == this.n;
    }

    // построение еще не закончено
    public boolean isValid() {
        // Все столбцы и строчки
        for (int i = 0; i < this.n; i++) {
            int row = 0, col = 0;
            for (int j = 0; j < n; j++) {
                row += field[i][j];
                col += field[j][i];
            }
            if (row > 1 || col > 1) {
                return false;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int diagonal1 = 0, diagonal2 = 0;
            for (int j = 0; j < n; j++) {
                if ((i + j) < n) {
                    diagonal1 += field[j][i + j];
                    diagonal2 += field[i + j][j];
                } else {
                    break;
                }
            }
            if (diagonal1 > 1 || diagonal2 > 1) {
                return false;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            int diagonal1 = 0, diagonal2 = 0;
            for (int j = 0; j < n; j++) {
                int temp = i - j - 1;
                if (temp >= 0 && temp < n) {
                    diagonal1 += field[temp][j];
                    diagonal2 += field[n - temp - 1][n - j - 1];
                } else {
                    break;
                }
            }
            if (diagonal1 > 1 || diagonal2 > 1) {
                return false;
            }
        }

        return true;
    }

    public Board clone() {
        Board b = new Board(this.n);
        for (int i = 0; i < this.n; i++) {
            System.arraycopy(this.field[i], 0, b.field[i], 0, this.n);
        }
        return b;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                str.append(this.field[i][j]).append(' ');
            }
            str.append('\n');
        }
        return String.valueOf(str);
    }
}
