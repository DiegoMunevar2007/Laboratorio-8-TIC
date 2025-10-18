import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private int capacity;
    private Queue<Item> queue;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void put(Thread thread, Item item) {
        while (queue.size() >= capacity) {
            try {
                System.out.println("[" + thread.getName() + "]: espera porque el búfer está lleno");
                wait();
                System.out.println("[" + thread.getName() + "]: despertó porque hay espacio en el búfer");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(item);
        System.out.println("[" + thread.getName() + "]: guardó en el búfer el " + item.getName());
    }

    public synchronized Item get(Thread thread) {
        Item item = queue.poll();
        if (item != null) {
            System.out.println("[" + thread.getName() + "]: agarró del búfer el " + item.getName());
            notify();
        }
        return item;
    }
}