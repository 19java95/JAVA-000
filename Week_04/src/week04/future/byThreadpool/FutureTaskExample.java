package week04.future.byThreadpool;



import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author tangtian
 * @version 1.0
 * @className FutureExample
 * @description
 * @date 2020/11/9 8:48 AM
 **/
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.shutdown();
        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
