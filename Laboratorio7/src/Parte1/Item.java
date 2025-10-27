package Parte1;

public class Item {

    private String name;
    private boolean processed;

    public Item(String name) {
        this.name = name;
        this.processed = false;
    }

    public String getName() {
        return name;
    }

    public synchronized void waitUntilProcessed(Thread thread) throws InterruptedException {
        while (!processed) {
            wait();
        }
    }

    public synchronized void markProcessedAndNotify(Thread thread) {
        this.processed = true;
        notifyAll();
    }
}