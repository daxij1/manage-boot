package com.daxij1.manageboot.framework.codegenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 10:19
 * @description：MybatisPlusCodeGenerator
 */
public class MybatisPlusCodeGenerator {

    public static void main(String[] args) {
        gen();
    }
    
    private static void gen(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取当前项目根路径
        String projectPath = "D:\\idea\\workspace\\my\\manage-boot";
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("daxij1");
        gc.setOpen(false); //不打开生产的文件
        gc.setFileOverride(false); //不覆盖之前生成的文件
        gc.setServiceName("%Service");
        gc.setIdType(IdType.AUTO);// 主键策略 自增  注意要和数据库中表实际情况对应
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);//自动开启swagger2的支持
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:h2:file:D:\\idea\\workspace\\my\\manage-boot\\h2db\\db");
        dsc.setDriverName("org.h2.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.H2);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("manageboot");
        pc.setParent("com.daxij1");
        //pc.setService("service");
        pc.setService("delete");//生成service，进行删除
        pc.setServiceImpl("delete");//生成serviceimpl，进行删除
        pc.setController("delete");//生成controller，进行删除
        //pc.setController("controller");
        pc.setEntity("pojo.entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("T_USER");
        strategy.setNaming(NamingStrategy.underline_to_camel);// 下划线转驼峰
        strategy.setTablePrefix("t_");//去掉t_这个前缀后生成类名
        strategy.setEntityLombokModel(true);// 自动生成lombok注解  记住要有lombok依赖和对应的插件哈
        strategy.setLogicDeleteFieldName("is_deleted");//设置逻辑删除字段 要和数据库中表对应哈

        // 设置创建时间和更新时间自动填充策略
        TableFill created_date = new TableFill("created_date", FieldFill.INSERT);
        TableFill updated_date = new TableFill("updated_date", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(created_date);
        tableFills.add(updated_date);
        strategy.setTableFillList(tableFills);

        // 乐观锁策略
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);//采用restful 风格的api
        strategy.setControllerMappingHyphenStyle(true); // controller 请求地址采用下划线代替驼峰
        mpg.setStrategy(strategy);

        // 执行
        mpg.execute();
    }
    
}
