package com.frog.authority.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author frog
 * @link https://baomidou.com/pages/4c6bcf/
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 乐观锁初始版本: 0
     */
    private final Integer DEFAULT_VERSION = 0;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "lastUpdateTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "version", DEFAULT_VERSION);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "lastUpdateTime", LocalDateTime.now());
    }
}