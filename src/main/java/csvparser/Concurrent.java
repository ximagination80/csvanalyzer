package csvparser;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Concurrent {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 5;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "THREAD #" + mCount.getAndIncrement());
        }
    };

    private static final BlockingQueue<Runnable> RUNNABLE_BLOCKING_QUEUE =
            new LinkedBlockingQueue<>(100);

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, RUNNABLE_BLOCKING_QUEUE, THREAD_FACTORY);

    private Concurrent() {
        // hidden
    }
}
