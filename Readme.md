## 1.项目介绍
- manage-boot是基于springboot的一款纯净脚手架，免费开源。如果你是一位有经验的后端开发，manage-boot能帮助你快递搭建后台管理系统，如果你是一位java初学者，manage-boot也能为你贯穿javaweb开发中的各种知识，是一个不错的学习项目
- 相比于其他的一些脚手架工具，manage-boot优势在于小而巧，提供了最基本的用户管理、菜单管理、角色管理、权限管理、代码生成功能，默认采用h2数据库，无需依赖其他数据库即可部署运行，并提供了多数据源支持
- 前端使用vue+elementui，代码清晰简洁，对后端开发人员非常友好，定制开发十分方便

## 2.快速开始

### 2.1 初始化项目

首先，确保你的pc已经安装了以下软件

```sh
1.64bit JDK 1.8+
2.maven 3.2.x
```

- 拉取代码

clone代码，重命名文件夹、pom.xml中的artifactId、index.html中的title，如改为manage-boot-crm

- 启动项目

第一次启动，spring.datasource.initializationMode设为EMBEDDED，启动时会初始化系统运行需要的表和数据，若设为NEVER，需自行执行schema.sql和data.sql中的语句初始化数据

- 访问

http://localhost:9999
admin/123456

### 2.2 使用多数据源

项目默认使用的h2数据库，可以将其替换为mysql、oracle或其他库，修改spring.datasource.dynamic.datasource配置即可。该示例使用多数据源，将业务库添加到项目中。

添加mysql驱动

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.26</version>
</dependency>
```

添加多数据源配置
```properties
spring.datasource.business.url=jdbc:mysql://localhost:3306/crm
spring.datasource.business.username=root
spring.datasource.business.password=123456
spring.datasource.business.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 2.3 搭建管理页面

以下面商品表为例，快速搭建对该表的增删改查页面，首先创建商品表

```sql
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `price` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0下架1上架',
  `descr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `createtime` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `lastmodifiedtime` timestamp(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `t_goods` VALUES (1, '苹果', '12', '1', '可以吃的苹果', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `t_goods` VALUES (2, '可乐', '3', '1', '可以喝的可乐', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `t_goods` VALUES (3, 'T恤', '199', '1', '可以穿的T恤', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `t_goods` VALUES (4, '汽车', '159999', '1', '可以跑的汽车', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

- 生成代码

打开类MybatisPlusCodeGenerator.java，修改项目根路径projectPath、数据库连接配置DataSourceConfig和需要生成代码的表名称strategy.setInclude("t_goods")，运行该类的main方法生成代码

- 添加列表、新增/修改、删除接口

```java
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daxij1.manageboot.framework.pojo.ResponseVO;
import com.daxij1.manageboot.pojo.entity.Goods;
import com.daxij1.manageboot.pojo.param.GoodsAddOrUpdateParam;
import com.daxij1.manageboot.pojo.param.GoodsQueryParam;
import com.daxij1.manageboot.service.GOODService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/goods")
// 指定数据源
@DS("business")
public class GoodsController {
    
    @Autowired
    private GOODService goodService;

    @PostMapping("/list")
    public ResponseVO<PageInfo<Goods>> list(@Valid @RequestBody GoodsQueryParam param){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getName())) {
            queryWrapper.like("name", "%" + param.getName() + "%");
        }
        PageHelper.startPage(param.getPageno(), param.getPagesize());
        List<Goods> list = goodService.list(queryWrapper);
        return ResponseVO.success(new PageInfo<>(list));
    }

    @PostMapping("/addOrUpdate")
    public ResponseVO<Object> addOrUpdate(@Valid @RequestBody GoodsAddOrUpdateParam param){
        Integer id = param.getId();
        Goods goods = new Goods();
        BeanUtils.copyProperties(param, goods);
        if (id != null) { //更新
            goods.setLastmodifiedtime(new Date());
            goodService.updateById(goods);
        } else { //新增
            goodService.save(goods);
        }
        return ResponseVO.success();![输入图片说明](src/main/resources/static/img/%E5%95%86%E5%93%81%E7%AE%A1%E7%90%86.png)
    }

    @GetMapping("/delete")
    public ResponseVO<Object> delete(Integer id){
        if (id == null || id <= 0) {
            return ResponseVO.paramFail("商品id有误");
        }
        goodService.removeById(id);
        return ResponseVO.success();
    }

}
```

- 添加crm/goods.html页面

拷贝template.html到crm/goods.html中，修改引入静态资源的路径、表单查询字段名称、表单字段名称以及列表、新增/修改、删除接口的路径

- 添加菜单和权限

运行项目，在菜单管理和角色管理中配置商品管理页面

- 最终效果



## 3.界面风格

