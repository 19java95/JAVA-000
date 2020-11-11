package week04.tool.byThreadpool;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tangtian
 * @version 1.0
 * @className text1
 * @description countDownLatch
 * @date 2020/11/9 6:26 AM
 **/
public class CountDownLatchExample {
    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    sum();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("程序执行完毕");
        exec.shutdown();
    }
    private static int sum(){
        return fibo(39);
    }
    private static int fibo(int a){
        if (a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a - 2);
    }
}
