package utils;

/**
 * Created by marcin.bracisiewicz
 */
public class DelaySimulator {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
