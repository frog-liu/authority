package com.frog.authority.common.base.factory;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 *
 * @author liuhuan
 */
public class DefaultThreadFactory implements ThreadFactory {

    private final String poolName;

    private final AtomicInteger poolNumber = new AtomicInteger(1);

    private final ThreadGroup threadGroup;

    private final AtomicInteger threadNumber = new AtomicInteger(1);


    public DefaultThreadFactory() {
        this(null);
    }

    public DefaultThreadFactory(String poolName) {
        this.poolName = getThreadPoolName(poolName);
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            threadGroup = securityManager.getThreadGroup();
        } else {
            threadGroup = Thread.currentThread().getThreadGroup();
        }
    }

    private static final String DEFAULT_POOL_NAME = "AuthThreadPool";

    private String getThreadPoolName(String poolName) {
        if (StringUtils.isEmpty(poolName)) {
            poolName = DEFAULT_POOL_NAME;
        }
        if (!poolName.endsWith(StringPool.DASH)) {
            poolName = poolName + StringPool.DASH;
        }
        return poolName + poolNumber.getAndIncrement();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, poolName + StringPool.DASH + threadNumber.getAndIncrement());
        t.setDaemon(false);

        if (Thread.NORM_PRIORITY != t.getPriority()) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
