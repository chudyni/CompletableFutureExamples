package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFWithConsumer {

    public static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();

        //Does not return value
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));

        Assert.assertTrue("Result was empty", result.length() > 0);
    }
}
