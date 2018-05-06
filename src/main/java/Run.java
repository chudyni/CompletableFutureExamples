import samples.*;

/**
 * Created by marcin.bracisiewicz
 */
public class Run {

    //https://dzone.com/articles/20-examples-of-using-javas-completablefuture

    public static void main(String[] args) {

        // 1. Creating a Completed CompletableFuture
        CF.completedFutureExample();

        // 2. Running a Simple Asynchronous Stage
        CFAsyncStage.runAsyncExample();

        // 3. Applying a Function on the Previous Stage
        CFWithFunction.thenApplyExample();

        // 4. Asynchronously Applying a Function on a Previous Stage
        CFWithFuntionAsync.thenApplyAsyncExample();

        // 5. Asynchronously Applying a Function on  a Previous Stage Using a Custom Executor
        //MEMORY LEAK !
//        CFWithFunctionExecutorAsync.thenApplyAsyncWithExecutorExample();

        // 6. Consuming the Result of the Previous Stage
        CFWithConsumer.thenAcceptExample();

        // 7. Asynchronously Consuming the Result of the Previous Stage
        CFWithConusmerAsync.thenAcceptAsyncExample();

        // 8. Completing a Computation Exceptionally
        // NEEDS JAVA 9
//        CFComputationExceptionally.completeExceptionallyExample();

        // 9. Canceling a Computation
        // NEEDS JAVA 9
//        CFComputationExceptionallyCancel.cancelExample();

        // 10. Applying a Function to the Result of Either of Two Completed Stages
        // refactor to Java 9
        CFFunctionOnTwoStages.applyToEitherExample();

    }
}
