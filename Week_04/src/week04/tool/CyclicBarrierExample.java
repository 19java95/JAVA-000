package week04.tool;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author tangtian
 * @version 1.0
 * @className text1
 * @description CyclicBarrier
 * @date 2020/11/9 6:26 AM
 **/
public class CyclicBarrierExample {
    private static  CyclicBarrier barrier=new CyclicBarrier(2,()-> System.out.println("currentResult"));
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i< 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //等待线程准备好
                        System.out.println(Thread.currentThread().getName() + ":ready");
                        //开始执行任务
                        System.out.println(Thread.currentThread().getName() + "--result:" + sum());
                        //等待处理结果
                        barrier.await();
                        System.out.println("continue");
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
    private static int sum() throws BrokenBarrierException, InterruptedException {
        return fibo(39);
    }
    private static int fibo(int a){
        if (a < 2){
            return 1;
        }
        return fibo(a-1) + fibo(a - 2);
    }
}
