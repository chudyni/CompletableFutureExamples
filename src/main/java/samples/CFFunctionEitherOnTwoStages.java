package samples;

import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFFunctionEitherOnTwoStages {

    //The below example creates a CompletableFuture that applies a Function to the result of
    //either of two previous stages (no guarantees on which one will be passed to the Function).
    public static void applyToEitherExample() {
        String original = "Message";

        //use Java9 delayedUpperCase(s) - own method
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(string -> {
                    DelaySimulator.sleep(200);
                    return string.toUpperCase();
                });

        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original)
                .thenApplyAsync(string -> {
                    DelaySimulator.sleep(300);
                    return string.toLowerCase();
                }), s -> s + " from applyToEither"
        );

        //Java 9
//        Assert.assertTrue(cf2.join().endsWith(" from applyToEither"));
        System.out.println(cf2.join());
    }
}
