import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * Тот кто берет из очереди и делает какие то вычисления.
 */
public class BoardSolver implements Runnable {
    private BlockingQueue<Board> queue = null;
    private Set<String> result = null;

    BoardSolver(BlockingQueue<Board> queue, Set<String> result) {
        this.queue = queue;
        this.result = result;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // берем доску из очереди
                Board b = queue.take();

                if (b.isEnd()) {
                    // если нашли решение, то сохраняем его
//                    result.add(b.toString());
                    System.out.println(b.toString());
                } else {
                    // если нет, то ищем все след. комбинации и кладем их в очередь
                    Main.generateNextBoards(b);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
