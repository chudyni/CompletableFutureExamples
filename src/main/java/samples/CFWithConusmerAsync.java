package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFWithConusmerAsync {

    public
    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        Assert.assertTrue("Result was empty", result.length() > 0);
    }
}
