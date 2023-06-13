package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Brand> brands = brandService.selectAll();

        //转为json
        String jsonString = JSON.toJSONString(brands);

        //写回数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接受品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        System.out.println(params);

        //转换为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service添加
        brandService.add(brand);

        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 修改数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接受品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        System.out.println(params);
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接受品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转换为Brand对象
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service添加
        brandService.deleteByIds(ids);

        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收 当前页码，和 每页展示条数 url?currentPage=1 & pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //转为json
        String jsonString = JSON.toJSONString(pageBean);

        //写回数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收 当前页码，和 每页展示条数 url?currentPage=1 & pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //接受条件
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //转为json
        String jsonString = JSON.toJSONString(pageBean);

        //写回数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
}
