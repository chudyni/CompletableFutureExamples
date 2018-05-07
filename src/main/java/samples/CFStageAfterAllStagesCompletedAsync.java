package samples;

import org.junit.Assert;
import utils.DelaySimulator;
import utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by marcin.bracisiewicz
 */
public class CFStageAfterAllStagesCompletedAsync {

    public static void allOfAsyncExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg)
                        .thenApplyAsync(string -> {
                            DelaySimulator.sleep(200);
                            return string.toUpperCase();
                        }))
                .collect(Collectors.toList());

        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> {
                        Assert.assertTrue(StringUtils.isUpperCase(cf.getNow(null)));
                        result.append(cf.getNow(null));
                    });
                });

        allOf.join();
        Assert.assertEquals("ABC", result.toString());
    }
}
