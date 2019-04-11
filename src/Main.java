import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    static int n = 7;

    public static ArrayList<Board> getOnlyValidBoards(ArrayList<Board> boards) {
        ArrayList<Board> validBoards = new ArrayList<>();
        boards.forEach(board -> {
            if (board.isValid()) {
                validBoards.add(board);
            }
        });
        return validBoards;
    }

    public static ArrayList<Board> genNextBoards(Board board) {
        ArrayList<Board> boards = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.field[i][j] == 0) {
                    Board b = board.clone();
                    b.field[i][j] = 1;
                    boards.add(b);
                }
            }
        }

        return getOnlyValidBoards(boards);
    }

    static BlockingQueue<Board> queue = new LinkedBlockingDeque<>();

    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        // сгенерировали стартовую доску
        Board firstBoard = new Board(n);

        // сгенерировали всевозможные начальные комбинации (то есть на доске стоит 1 королева)
        queue.addAll(genNextBoards(firstBoard));

        while (!queue.isEmpty()) {
            // берем доску из очереди
            Board b = queue.take();
//            System.out.println(b.toString());
            if (b.isEnd()) {
                // если нашли решение, то сохраняем его
                result.add(b.toString());
            } else {
                // если нет, то ищем все след. комбинации и кладем их в очередь
                queue.addAll(genNextBoards(b));
            }
        }

        result.forEach(System.out::println);
        System.out.println(result.size());
    }
}
