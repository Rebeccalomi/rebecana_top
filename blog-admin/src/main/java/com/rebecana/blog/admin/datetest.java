package com.rebecana.blog.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class datetest {
    public static void main(String[] args) {
        //Date类型
        Date date = new Date();
        //格式类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //格式化日期类型为字符串
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}
