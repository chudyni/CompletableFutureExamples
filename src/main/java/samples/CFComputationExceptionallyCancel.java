package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by marcin.bracisiewicz
 */
public class CFComputationExceptionallyCancel {

    //Very close to exceptional completion, we can cancel a computation via the cancel(boolean mayInterruptIfRunning)
    // method from the Future interface. For CompletableFuture, the boolean parameter is not used because the
    // implementation does not employ interrupts to do the cancelation. Instead, cancel() is equivalent to
    // completeExceptionally(new CancellationException())
    public static void cancelExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase,
                        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");

        Assert.assertTrue("Was not canceled", cf.cancel(true));
        Assert.assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        Assert.assertEquals("canceled message", cf2.join());
    }
}
