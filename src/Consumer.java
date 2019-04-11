/**
 * Тот кто берет из очереди и делает какие то вычисления.
 */
public class Consumer {
    public boolean isSolved(Board board) {
        return true;
    }

    /**
     * Генерирует все след доски.
     */
    public Board[] nextBoards() {
        return new Board[]{};
    }

    public Board[] solveBrd(Board board) {
        if (isSolved(board)){
            return new Board[]{board};
        } else {
            return solveChilds();
        }
    }

    public Board[] solveChilds() {
        return new Board[]{};
    }

    public void solve() {
        // Взять доску из очереди
        // Проверить валидная ли она
    }
}
