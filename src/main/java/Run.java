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
        CFComputationExceptionally.completeExceptionallyExample();

        // 9. Canceling a Computation
        CFComputationExceptionallyCancel.cancelExample();

        // 10. Applying a Function to the Result of Either of Two Completed Stages
        // refactor to Java 9
        CFFunctionEitherOnTwoStages.applyToEitherExample();

        // 11. Consuming the Result of Either of Two Completed Stages
        // refactor to Java 9
        CFConsumerEitherOnTwoStages.applyToEitherExample();

        // 12. Running a Runnable Upon Completion of Both Stages
        CFRunnableBothOnTwoStages.runAfterBothExample();

        // 13. Accepting the Results of Both Stages in a BiConsumer
        CFBiConsumerBothOnTwoStages.thenAcceptBothExample();

        // 14. Applying a BiFunction on Results of Both Stages
        CFBiFunctionBothOnTwoStages.thenCombineExample();

        // 15. Asynchronously Applying a BiFunction on Results of Both Stages
        CFBiFunctionBothOnTwoStagesAsync.thenCombineAsyncExample();

        // 16. Composing CompletableFutures
        CFCompose.thenComposeExample();

        // 17. Creating a Stage That Completes When Any of Several Stages Completes
        CFStageAfterAnyStageCompleted.anyOfExample();

        // 18. Creating a Stage That Completes When All Stages Complete
        CFStageAfterAllStagesCompleted.allOfExample();

        // 19. Creating a Stage That Completes Asynchronously When All Stages Complete
        CFStageAfterAllStagesCompletedAsync.allOfAsyncExample();

        // 20. Real Life Example
    }
}
