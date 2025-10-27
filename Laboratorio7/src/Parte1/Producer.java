package Parte1;

public class Producer extends Thread {

    private static int totalToProduce;
    private static int producedCount = 0;
    private Buffer buffer;

    public Producer(String name, Buffer buffer) {
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            Item item = produce();
            if (item == null) {
                break;
            }
            buffer.put(this, item);
            try {
                System.out.println(
                        "[" + this.getName() + "]: Se duerme esperando a que el " + item.getName() + " sea procesado");
                item.waitUntilProcessed(this); // Espera hasta que el item sea procesado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[" + this.getName() + "]: finalizado");
    }

    private Item produce() {
        String itemName = null;
        synchronized (Producer.class) {
            if (producedCount < totalToProduce) {
                producedCount++;
                itemName = "ítem " + producedCount;
            }
        }
        if (itemName != null) {
            Item item = new Item(itemName);
            System.out.println("[" + this.getName() + "]: produjo el " + item.getName());
            return item;
        }
        return null;
    }

    public static void setTotalToProduce(int total) {
        totalToProduce = total;
    }
}