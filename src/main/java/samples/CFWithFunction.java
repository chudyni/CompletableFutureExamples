package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFWithFunction {

    public static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message")
                //thenApply - when previous stage completes normally without exception (it's already completed)
                .thenApply(String::toUpperCase);
        Assert.assertEquals("MESSAGE", cf.getNow(null));
    }
}
