package samples;

import org.junit.Assert;
import utils.AsyncSimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFFunctionOnTwoStages {

    public static void applyToEitherExample() {
        String original = "Message";

        //use Java9 delayedUpperCase(s) - own method
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(string -> {
                    AsyncSimulator.sleep(500);
                    return string.toUpperCase();
                });

        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original)
                .thenApplyAsync(string -> {
                    AsyncSimulator.sleep(300);
                    return string.toLowerCase();
                }), s -> s + " from applyToEither"
        );

        //Java 9
//        Assert.assertTrue(cf2.join().endsWith(" from applyToEither"));
        System.out.println(cf2.join());
    }
}
