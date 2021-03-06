package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFAsyncStage {

    public static void runAsyncExample() {

        //By default if no executor is set - asynchronous execution uses ForkJoinPool implementation,
        //which uses deamon threads to execute Runnable task - specific for CompletableFuture
        //Other CompletionStage implementations can override this behaviour.
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            Assert.assertTrue(Thread.currentThread().isDaemon());
            DelaySimulator.sleep(500);
        });

        Assert.assertFalse(cf.isDone());
        DelaySimulator.sleep(1000);
        Assert.assertTrue(cf.isDone());
    }

}
