package samples;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CF {

    public static void completedFutureExample() {
        //there is String passed - CompletableFuture is already completed
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        Assert.assertTrue(cf.isDone());

        //Returns null if not completed
        Assert.assertEquals("message", cf.getNow(null));
    }

}
