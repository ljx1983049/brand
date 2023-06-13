package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {
    /*
    查询所有
     */
    List<Brand> selectAll();

    /*
    添加功能
     */
    void add(Brand brand);

    /*
      批量删除
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize  每页显示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage , int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage , int pageSize ,Brand brand);
}
