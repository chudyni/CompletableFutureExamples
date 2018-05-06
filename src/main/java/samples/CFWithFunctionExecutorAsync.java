package samples;

import org.junit.Assert;
import utils.AsyncSimulator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by marcin.bracisiewicz
 */
public class CFWithFunctionExecutorAsync {

    private static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    public static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(string -> {
                    Assert.assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
                    Assert.assertFalse(Thread.currentThread().isDaemon());
                    AsyncSimulator.sleep(500);
                    return string.toUpperCase();
                },
                        executor);

        Assert.assertNull(cf.getNow(null));
        Assert.assertEquals("MESSAGE", cf.join());
    }
}
