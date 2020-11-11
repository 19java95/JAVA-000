package week04.tool;


import java.util.concurrent.Semaphore;

/**
 * @author tangtian
 * @version 1.0
 * @className Semaphore
 * @description
 * @date 2020/11/9 8:27 AM
 **/
public class SemaphoreExample {
    private final static int threadCount = 20;

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < threadCount; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();//获取许可
                    System.out.println(sum());//开始执行任务
                    semaphore.release();//释放个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

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
