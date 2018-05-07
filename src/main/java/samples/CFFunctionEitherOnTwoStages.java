package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by marcin.bracisiewicz
 */
public class CFFunctionEitherOnTwoStages {

    //The below example creates a CompletableFuture that applies a Function to the result of
    //either of two previous stages (no guarantees on which one will be passed to the Function).
    public static void applyToEitherExample() {
        String original = "Message";

        //use Java9 delayedUpperCase(s) - own method
//        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
//                .thenApplyAsync(string -> {
//                    DelaySimulator.sleep(200);
//                    return string.toUpperCase();
//                });
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase,
                        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));


//        CompletableFuture cf2 = cf1.applyToEither(
//                CompletableFuture.completedFuture(original)
//                .thenApplyAsync(string -> {
//                    DelaySimulator.sleep(300);
//                    return string.toLowerCase();
//                }), s -> s + " from applyToEither"
//        );
        CompletableFuture<String> cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original)
                        .thenApplyAsync(String::toLowerCase,
                                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)), s -> s + " from applyToEither"
        );

        Assert.assertTrue(cf2.join().endsWith(" from applyToEither"));
    }
}
