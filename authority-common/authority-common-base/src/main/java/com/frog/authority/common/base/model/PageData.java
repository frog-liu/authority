package com.frog.authority.common.base.model;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 分页信息
 *
 * @author frog
 */
@Data
@ApiModel(value = "分页查询结果")
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = -3788727443953486219L;

    @ApiModelProperty(value = "数据总条数")
    private long total;

    @ApiModelProperty(value = "总页数")
    private long pages;

    @ApiModelProperty(value = "返回页大小")
    private long size;

    @ApiModelProperty(value = "返回数据")
    private Collection<T> records;

    private PageData() {}

    public static<T> PageData<T> getInstance(List<T> record) {
        PageData<T> pageData = new PageData<>();
        pageData.setSize(record.size());
        PageInfo<T> pageInfo = new PageInfo<>(record);
        pageData.setTotal(pageInfo.getTotal());
        pageData.setPages(pageInfo.getPages());
        pageData.setRecords(record);
        return pageData;
    }

}
