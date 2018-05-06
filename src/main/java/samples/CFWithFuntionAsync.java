package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFWithFuntionAsync {

    public static void thenApplyAsyncExample() {

        //Executes asynchronously using ForkJoinPool.commonPool()
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(string -> {
                    Assert.assertTrue(Thread.currentThread().isDaemon());
                    DelaySimulator.sleep(1000);
                    return string.toUpperCase();
                });

        Assert.assertNull(cf.getNow(null));

        // get method  - to block the current thread until this result will be provided
        //CompletableFuture.join() method is similar to the get method, but it throws an unchecked exception in case the Future does not complete normally
        Assert.assertEquals("MESSAGE", cf.join());
    }
}
