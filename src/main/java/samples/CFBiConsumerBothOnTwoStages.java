package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFBiConsumerBothOnTwoStages {

    public static void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();

        CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .thenAcceptBoth(
                        CompletableFuture.completedFuture(original)
                        .thenApply(String::toLowerCase),
                        (s1, s2) -> result.append(s1 + s2)
                );

        Assert.assertEquals("MESSAGEmessage", result.toString());
    }
}
