public class CpuLoad {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        long startTime = System.currentTimeMillis();
        long runDuration = 5 * 60 * 1000; // 5 minutes in milliseconds

        // Launch multiple threads to simulate a heavy CPU load
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(() -> {
                while (System.currentTimeMillis() - startTime < runDuration) {
                    double value = Math.random();
                    long actionStartTime = System.currentTimeMillis();
                    for (int j = 0; j < 1_000_000; j++) {
                        value = Math.sin(value);
                    }
                    long actionEndTime = System.currentTimeMillis();
                    double timeDifferenceInSeconds = (actionEndTime - actionStartTime) / 1000.0;

                    System.out.println("Operation Time: " + timeDifferenceInSeconds + " seconds");
                    try {
                        // Adjust this value if you want to control CPU usage more precisely
                        Thread.sleep((long) (timeDifferenceInSeconds * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
