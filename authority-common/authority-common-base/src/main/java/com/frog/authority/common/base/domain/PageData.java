package com.frog.authority.common.base.domain;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
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
    private List<T> records;

    private PageData() {}

    public static<T> PageData<T> getInstance(List<T> list) {
        PageData<T> pageData = new PageData<>();
        pageData.setSize(list.size());
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageData.setTotal(pageInfo.getTotal());
        pageData.setPages(pageInfo.getPages());
        pageData.setRecords(list);
        return pageData;
    }

}
