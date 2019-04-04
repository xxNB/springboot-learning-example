package demo.springboot.service;


import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service("redissonService")
public class RedissonService {

    @Resource
    private RedissonClient redissonClient;

    public void getRedissClient() throws IOException{
        Config config = redissonClient.getConfig();
        System.out.println(config.toJSON().toString());
    }

    public <T>RBucket<T> getBucket(String objectName){
        RBucket<T> bucket = redissonClient.getBucket(objectName);
        return bucket;
    }

    public <K, V> RMap<K, V> getRMap(String objectName){
        RMap<K, V> map = redissonClient.getMap(objectName);
        return map;
    }

    /**
    * 获得锁
    * */

    public RLock getRLock(String  objectName){
        RLock rlock = redissonClient.getLock(objectName);
        return rlock;
    }

    /**
     * 获取读取锁
     *
     * */

    public RReadWriteLock getRWLock(String objectName){
        RReadWriteLock rwlock = redissonClient.getReadWriteLock(objectName);
        return rwlock;
    }

    /**
     * 获取原子数
     *
     * */

    public RAtomicLong getRAtomicLong(String objectName){
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取计数锁
     *
     *
     * ***/
    public RCountDownLatch getRCountDownLatch (String objectName){
        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     *
     * 获取消息的Topic
     *
     * **/
    public <M> RTopic<M> getRTopic(String objectName){
        RTopic<M> rTopic = redissonClient.getTopic(objectName);
        return rTopic;
    }

}
