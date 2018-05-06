package samples;

import utils.DelaySimulator;

import java.util.concurrent.CompletableFuture;

/**
 * Created by marcin.bracisiewicz
 */
public class CFConsumerEitherOnTwoStages {

    public static void applyToEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();

        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(string -> {
                    DelaySimulator.sleep(500);
                    return string.toUpperCase();
                })
                .acceptEither(
                        CompletableFuture.completedFuture(original)
                        .thenApplyAsync(string -> {
                            DelaySimulator.sleep(400);
                            return string.toLowerCase();
                        }), s -> result.append(s).append(" acceptEither")
                );

        cf.join();
        //Java 9
//        Assert.assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
        System.out.println(result);

    }
}
