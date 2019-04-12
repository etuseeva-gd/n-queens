import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
//    static int n = 7;
//    static int threads_len = 3;

    static BlockingQueue<Board> queue = new LinkedBlockingDeque<>();
    static Set<String> result = new HashSet<>();

    public static void generateNextBoards(Board board) {
        int n = board.n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.field[i][j] == 0) {
                    // выставляем куда нибудь королеву
                    Board b = board.clone();
                    b.field[i][j] = 1;

                    // кладем в очередь только валидные доски с королевами
                    if (b.isValid()) {
//                        System.out.println(b.toString());
                        queue.add(b);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите n:");
        int n = Integer.parseInt(sc.nextLine());

        System.out.println("Введите число потоков:");
        int threads_len = Integer.parseInt(sc.nextLine());

        // сгенерировали стартовую доску
        Board board = new Board(n);
        generateNextBoards(board);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threads_len; i++) {
            threads.add(new Thread(new BoardSolver(queue, result)));
        }

        for (int i = 0; i < threads_len; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < threads_len; i++) {
            threads.get(i).join();
        }

        AtomicInteger i = new AtomicInteger();
        result.forEach( b -> {
            i.getAndIncrement();
            System.out.println(b);
        });
        System.out.println(i);
    }
}
