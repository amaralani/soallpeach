package ir.maralani;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class CounterHandler implements Runnable {
    private static BlockingDeque<String> list = new LinkedBlockingDeque<>();
    private static int count = 0;

    public static void addNumber(String string) {
        list.add(string);
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String string = list.take();
                count += Integer.parseInt(string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
