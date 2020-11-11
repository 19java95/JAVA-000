package week04.tool;


import java.util.concurrent.CountDownLatch;

/**
 * @author tangtian
 * @version 1.0
 * @className text1
 * @description countDownLatch
 * @date 2020/11/9 6:26 AM
 **/
public class CountDownLatchExample {
    private final static int threadCount = 20;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        long start=System.currentTimeMillis();
        for (int i = 0; i< threadCount; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(sum());//开始执行任务
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
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
