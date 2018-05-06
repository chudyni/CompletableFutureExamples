package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFBiFunctionBothOnTwoStagesAsync {

    //Since the two stages upon which CompletableFuture depends both run asynchronously, the thenCombine() method
    //executes asynchronously, even though it lacks the Async suffix.
    //Therefore, we need to join() on the combining CompletableFuture to wait for the result.
    public static void thenCombineAsyncExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(string -> {
                    DelaySimulator.sleep(200);
                    return string.toUpperCase();
                })
                .thenCombine(
                        CompletableFuture.completedFuture(original)
                        .thenApplyAsync(string -> {
                            DelaySimulator.sleep(300);
                            return string.toLowerCase();
                        }), (s1, s2) -> s1 + s2);

        Assert.assertEquals("MESSAGEmessage", cf.join());
    }
}
