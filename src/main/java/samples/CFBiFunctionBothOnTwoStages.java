package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFBiFunctionBothOnTwoStages {

    //If the dependent CompletableFuture is intended to combine the results of two previous CompletableFutures by
    //applying a function on them and returning a result, we can use the method thenCombine()
    // The entire pipeline is synchronous, so getNow() at the end would retrieve the final result,
    public static void thenCombineExample() {
        String original = "Message";
        CompletableFuture  cf = CompletableFuture.completedFuture(original)
                .thenApply(string -> {
                    DelaySimulator.sleep(200);
                    return string.toUpperCase();
                })
                .thenCombine(
                        CompletableFuture.completedFuture(original)
                        .thenApply(string -> {
                            DelaySimulator.sleep(200);
                            return string.toLowerCase();
                        }), (s1, s2) -> s1 + s2);

        Assert.assertEquals("MESSAGEmessage", cf.getNow(null));
    }
}
