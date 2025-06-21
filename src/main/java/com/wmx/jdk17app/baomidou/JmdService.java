package com.wmx.jdk17app.baomidou;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Map;

/**
 * Springboot + JdbcTemplate + hikari 多数据源使用
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2022/2/23 17:14
 */
@Service
public class JmdService {

    private static final Logger log = LoggerFactory.getLogger(JmdService.class);

    /**
     * 默认数据源模板
     */
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 使用默认的数据源进行操作
     * 此时可以省略@DS注解。
     */
    // @DS("master")
    public List<Map<String, Object>> defaultDataSource() {
        showDs(jdbcTemplate);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from emp limit 100");
        return mapList;
    }

    /**
     * 测试事物是否正常回滚
     */
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() {
        String insertSql1 = "INSERT INTO emp (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)";
        String insertSql2 = "INSERT INTO emp (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)";
        Object[] args1 = {1, "张三", "Java开发", 7839, DateUtil.parseDate("1993/06/05"), 25000.5F, 1500, 10};
        Object[] args2 = {2, "李四", "VUe开发", 7839, DateUtil.parseDate("1995/04/05"), 21000.5F, 1200, 10};
        jdbcTemplate.update(insertSql1, args1);
        jdbcTemplate.update(insertSql2, args2);
        System.out.println(1/0);
    }

    /**
     * 使用 workflow 数据源进行操作
     *
     * @param
     */
    @DS("workflow")
    public List<Map<String, Object>> workflowDataSource() {
        showDs(jdbcTemplate);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from sys_config limit 100");
        return mapList;
    }

    /**
     * 使用 workflow 数据源进行操作
     *
     * @param
     */
    @DS("element")
    public List<Map<String, Object>> elementDataSource() {
        showDs(jdbcTemplate);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from user limit 100");
        return mapList;
    }

    /**
     * 数据源>>>>>>class com.baomidou.dynamic.datasource.DynamicRoutingDataSource
     * 连接>>>>>>>>HikariProxyConnection@622576000 wrapping com.mysql.cj.jdbc.ConnectionImpl@5a350156
     * 连接地址>>>>jdbc:mysql://127.0.0.1:3306/sys?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
     * 驱动名称>>>>MySQL Connector/J
     * 驱动版本>>>>mysql-connector-j-9.3.0 (Revision: 20ef8ee9eb4294a03858acccea0ddad3525f1ff9)
     * 数据库名称>>MySQL
     * 数据库版本>>8.0.21
     * 连接用户名称>root@localhost
     * <p>
     * 数据源>>>>>>class com.baomidou.dynamic.datasource.DynamicRoutingDataSource
     * 连接>>>>>>>>HikariProxyConnection@1148720328 wrapping com.mysql.cj.jdbc.ConnectionImpl@927421c
     * 连接地址>>>>jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
     * 驱动名称>>>>MySQL Connector/J
     * 驱动版本>>>>mysql-connector-j-9.3.0 (Revision: 20ef8ee9eb4294a03858acccea0ddad3525f1ff9)
     * 数据库名称>>MySQL
     * 数据库版本>>8.0.21
     * 连接用户名称>root@localhost
     * <p>
     * 数据源>>>>>>class com.baomidou.dynamic.datasource.DynamicRoutingDataSource
     * 连接>>>>>>>>HikariProxyConnection@702172207 wrapping com.mysql.cj.jdbc.ConnectionImpl@46373dc2
     * 连接地址>>>>jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
     * 驱动名称>>>>MySQL Connector/J
     * 驱动版本>>>>mysql-connector-j-9.3.0 (Revision: 20ef8ee9eb4294a03858acccea0ddad3525f1ff9)
     * 数据库名称>>MySQL
     * 数据库版本>>8.0.21
     * 连接用户名称>root@localhost
     *
     * @param jdbcTemplate
     */
    private void showDs(JdbcTemplate jdbcTemplate) {
        try {
            DataSource dataSource = jdbcTemplate.getDataSource();
            Connection connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("数据源>>>>>>" + dataSource.getClass());
            System.out.println("连接>>>>>>>>" + connection);
            System.out.println("连接地址>>>>" + connection.getMetaData().getURL());
            System.out.println("驱动名称>>>>" + metaData.getDriverName());
            System.out.println("驱动版本>>>>" + metaData.getDriverVersion());
            System.out.println("数据库名称>>" + metaData.getDatabaseProductName());
            System.out.println("数据库版本>>" + metaData.getDatabaseProductVersion());
            System.out.println("连接用户名称>" + metaData.getUserName());
        } catch (Exception e) {
            log.error(ExceptionUtil.stacktraceToString(e, Integer.MAX_VALUE));
        }
    }

}
