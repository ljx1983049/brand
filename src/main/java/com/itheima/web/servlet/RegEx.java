package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PiPei;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/regEx/*")
public class RegEx extends BaseServlet{

    /**
     * 正常匹配字符
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void piPei(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        BufferedReader br = req.getReader();
        String params = br.readLine();

        //转换为PiPei对象
        PiPei pipei = JSON.parseObject(params, PiPei.class);

        //定义正则表达式
        String regex = pipei.getInput();
        //指定要匹配的文本
        String str = pipei.getTextarea();

        Pattern patten = Pattern.compile(regex);//编译正则表达式
        Matcher matcher = patten.matcher(str);// 指定要匹配的字符串

        List<String> matchStrs = new ArrayList<>();

        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            matchStrs.add(matcher.group());//获取当前匹配的值
        }

        String wri="";

        for (int i = 0; i < matchStrs.size(); i++) {
            wri += matchStrs.get(i)+"\n";
        }

//        boolean matches = str.matches(regex);

        res.getWriter().write(wri);

    }

    /**
     * 匹配是否符合匹配规范
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void number(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        //转换为PiPei对象
        PiPei pipei = JSON.parseObject(params, PiPei.class);

        //定义正则表达式
        String regex = pipei.getInput();
        //指定要匹配的文本
        String str = pipei.getTextarea();

        if(str.matches(regex)){
            res.getWriter().write("success");
        }

    }

    /**
     * 替换的方法
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void replace(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();

        //转换为PiPei对象
        PiPei pipei = JSON.parseObject(params, PiPei.class);

        //接受路径的参数
        String textarea_tow = req.getParameter("textarea_tow");

        //定义正则表达式
        String regex = pipei.getInput();
        //指定要匹配的文本
        String str = pipei.getTextarea();

        Pattern patten = Pattern.compile(regex);//编译正则表达式
        Matcher matcher = patten.matcher(str);// 指定要匹配的字符串

        List<String> matchStrs = new ArrayList<>();

        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            matchStrs.add(matcher.group());//获取当前匹配的值
        }

        String wri="";

        for (int i = 0; i < matchStrs.size(); i++) {
            wri += matchStrs.get(i);
        }

        //替换指定的字段,变成新的字符串
        String s = str.replaceAll(wri, textarea_tow);

        res.getWriter().write(s);

    }

}
