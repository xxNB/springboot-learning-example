package demo.springboot;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountRateLimterDemo1 {
    private static AtomicInteger count = new AtomicInteger(0);

    private static void exec(){
        if(count.get()>=5){
            System.out.println("请求用户过, 请稍后在试!"+System.currentTimeMillis()/1000);
        }else {
            count.incrementAndGet();
        }try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            count.decrementAndGet();
        }
    }
}
