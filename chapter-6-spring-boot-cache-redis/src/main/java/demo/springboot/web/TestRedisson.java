package demo.springboot.web;

import demo.springboot.service.RedissonService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController("/test")
public class TestRedisson {

    @Resource
    private RedissonService redissonService;


    @GetMapping("/")
    @ResponseBody
    public void test(@RequestParam(name="record_id") String recordId){
        RLock lock = redissonService.getRLock(recordId);
        try {
            boolean bs = lock.tryLock(5, 6, TimeUnit.SECONDS);
            if(bs){
                log.info("进入业务代码:"+recordId);
                lock.unlock();
            }else {
                Thread.sleep(300);
            }
        }catch (Exception e){
            log.error(e.toString());
            lock.unlock();
        }
    }

}
