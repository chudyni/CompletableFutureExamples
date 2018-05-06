package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFCompose {

    //Result like in CFBiFunctionBothOnTwoStagesAsync and CFBiFunctionBothOnTwoStages
    //This method waits for the first stage (which applies an uppercase conversion) to complete. Its result is passed
    // to the specified Function, which returns a CompletableFuture, whose result will be the result of the returned
    // CompletableFuture.
    public static void thenComposeExample() {
        String original = "Message";

        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApply(string -> {
                    DelaySimulator.sleep(200);
                    return string.toUpperCase();
                })
                .thenCompose(upper -> CompletableFuture.completedFuture(original)
                        .thenApply(string -> {
                            DelaySimulator.sleep(200);
                            return string.toLowerCase();
                        })
                        .thenApply(s -> upper + s)
                );

        Assert.assertEquals("MESSAGEmessage", cf.join());
    }
}
