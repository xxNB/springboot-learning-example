package demo.springboot;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimiterDemo {
    private static RateLimiter limiter = RateLimiter.create(5);

    public static void exec(){
        limiter.acquire(1);
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("--"+System.currentTimeMillis()/1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
