package gameoflife;

import java.lang.management.ManagementFactory;
import java.util.function.Consumer;

public class CountingOutput implements Consumer<boolean[][]> {
    static final int TIMED_STEPS = 1000;
    static final int WARMUP_STEPS = TIMED_STEPS;

    private int steps = 0;
    private long timingStartNanos;

    public CountingOutput() {
        System.out.printf("Startup time: %dms%nWarming up", System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime());
    }

    @Override
    public void accept(boolean[][] cells) {
        steps++;
        if ((steps & 0xf) == 0) {
            System.out.print(".");
        }
        if (steps == WARMUP_STEPS) {
            System.out.printf("%nStarting timer");
            timingStartNanos = System.nanoTime();
        } else if (steps == WARMUP_STEPS + TIMED_STEPS) {
            long millis = (System.nanoTime() - timingStartNanos) / 1_000_000;
            System.out.printf("%nCompleted %d steps in %dms = %f steps per second%n", TIMED_STEPS, millis, TIMED_STEPS / (millis / 1000.0));
            System.exit(0);
        }
    }
}
