package samples;

import org.junit.Assert;
import utils.DelaySimulator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by marcin.bracisiewicz
 */
public class CFStageAfterAnyStageCompleted {

    //The below example illustrates how to create a CompletableFuture that completes when any of several
    // CompletableFutures completes, with the same result. Several stages are first created, each converting a string
    // from a list to uppercase. Because all of these CompletableFutures are executing synchronously (using thenApply()),
    // the CompletableFuture returned from anyOf() would execute immediately, since by the time it is invoked, all
    // stages are completed. We then use the whenComplete(BiConsumer<? super Object, ? super Throwable> action),
    // which processes the result (asserting that the result is uppercase).
    public static void anyOfExample() {
        StringBuilder result = new StringBuilder();
        List messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg)
//                        .thenApply(string -> {
//                            DelaySimulator.sleep(200);
//                            return (String)string.toUpperCase();
//                        })
                        .thenApply(string -> string.toString())
                )
                .collect(Collectors.toList());

        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((res, th) -> {
                        if(th == null) {
                            Assert.assertTrue(isUpperCase((String) res));
                            result.append(res);
                        }
                });

        Assert.assertTrue("Result was empty", result.length() > 0);
    }

    private static boolean isUpperCase(String word) {
        for(char c : word.toCharArray()) {
            if(!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
}
