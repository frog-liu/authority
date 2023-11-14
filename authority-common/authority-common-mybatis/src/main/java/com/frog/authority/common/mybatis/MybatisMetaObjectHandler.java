package com.frog.authority.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.frog.authority.security.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MybatisPlus 自动填充
 *
 * @author frog
 * @link <a href="https://baomidou.com/pages/4c6bcf/">自动填充</a>
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * This method is called by MyBatis when an entity is being inserted into the database. It fills certain fields with values.
     * @param metaObject A MetaObject instance representing the entity being inserted.
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createBy", SecurityUtils.getCurrentUserId());
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "lastUpdateBy", SecurityUtils.getCurrentUserId());
        this.fillStrategy(metaObject, "lastUpdateTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "version", 0);
    }

    /**
     * This method is called by MyBatis when an entity is being updated in the database. It fills certain fields with values.
     * @param metaObject A MetaObject instance representing the entity being updated.
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastUpdateBy", SecurityUtils.getCurrentUserId(), metaObject);
        this.setFieldValByName("lastUpdateTime", LocalDateTime.now(), metaObject);
    }

}