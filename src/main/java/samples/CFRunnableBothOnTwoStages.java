package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFRunnableBothOnTwoStages {

    //This example shows how the dependent CompletableFuture that executes a Runnable triggers upon completion of both
    // of two stages. Note that all the stages below run synchronously, where a stage first converts a message string
    // to uppercase, then a second converts the same message string to lowe
    public static void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();

        CompletableFuture.completedFuture(original)
//                .thenApply(String::toUpperCase)
                .thenApply(s -> result.append(s.toUpperCase()))
                .runAfterBoth(
                        CompletableFuture.completedFuture(original)
//                        .thenApply(String::toLowerCase),
                        .thenApply(s -> result.append(s.toLowerCase())),
                        () -> result.append(" done")
                );

        Assert.assertTrue("Result was empty", result.length() > 0);
        System.out.println(result);
    }
}
