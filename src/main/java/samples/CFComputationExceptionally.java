package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by marcin.bracisiewicz
 */
public class CFComputationExceptionally {

    // asynchronous operation can be explicitly completed exceptionally, indicating a failure in the computation.
    public static void completeExceptionallyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase,
                    CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

        //handles any exception by returning another message "message upon cancel"
        CompletableFuture exceptionHandler = cf.handle((s, th) -> (th == null) ? "message upon cancel" : "");

        //does not get into catch !!
        try {
            cf.join();
        } catch(CompletionException ex) { // just for testing
            Assert.assertEquals("completed exceptionally", ex.getCause().getMessage());
        }
        Assert.assertEquals("message upon cancel", exceptionHandler.join());
    }
}
