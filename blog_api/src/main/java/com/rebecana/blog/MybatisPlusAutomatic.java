package com.rebecana.blog;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

public class MybatisPlusAutomatic {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //生成路径
        gc.setOutputDir(projectPath + "/blog_api/src/main/java/");
        //设置作者
        gc.setAuthor("zdy");
        gc.setOpen(false);
        //第二次生成会把第一次生成的覆盖
        gc.setFileOverride(false);
        //生成的service接口名字首字母是否为I，这样设置就没有
        gc.setServiceName("%sService");
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setControllerName("%sController");
        //生成resultMap
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

        //2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 这里切换成自己连接的数据库地址信息
        dsc.setUrl("jdbc:mysql://175.178.1.135:3307/blog?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1193479622");

        // 连接数据的类型
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 3、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("");
        pc.setEntity("com.rebecana.blog.dao.pojo");
        pc.setService("com.rebecana.blog.service");
        pc.setMapper("com.rebecana.blog.dao.mapper");
        pc.setServiceImpl("com.rebecana.blog.service.impl");
        pc.setController("com.rebecana.blog.controller");
        pc.setXml("/resources/mapper/");
        mpg.setPackageInfo(pc);

        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //使用lombok
        //hospital_info为想要生成的表名，想要一下对多个表生成，用逗号分隔
        strategy.setInclude(scanner("ArticleBody").split(","));
        strategy.setTablePrefix("ms_");
        strategy.setEntityLombokModel(true);
        //表名前缀过滤
        //strategy.setTablePrefix("insurance_");
        mpg.setStrategy(strategy);

        TemplateConfig templateConfig = new TemplateConfig();

        //控制 不生成 controller
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //5、执行
        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
