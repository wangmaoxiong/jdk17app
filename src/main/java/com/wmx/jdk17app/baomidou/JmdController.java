package com.wmx.jdk17app.baomidou;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;
import com.wmx.jdk17app.pojo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Springboot + JdbcTemplate + hikari 多数据源使用
 * 注入同一类型全部实现类的实例
 * 参考文档：https://blog.csdn.net/co_zjw/article/details/144949823
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2022/2/23 17:24
 */
@RestController
public class JmdController {

    @Resource
    private JmdService jmdService;

    /**
     * dynamic-datasource 项目支持url,username,password加密
     * 使用RAS加密
     * 简单来说就是生成两把钥匙，私钥加密，公钥解密。
     * 公钥可以发布出去，用于项目启动时自动解密。
     * 将生成public-key、以及加密的内容放在yml配置文件中即可
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String[] arr = CryptoUtils.genKeyPair(512);
        System.out.println("privateKey:  " + arr[0]);
        System.out.println("publicKey:  " + arr[1]);
        System.out.println("url:  ENC(" + CryptoUtils.encrypt(arr[0], "jdbc:h2:~/test_frame;AUTO_SERVER=TRUE") + ")");
        System.out.println("username:  ENC(" + CryptoUtils.encrypt(arr[0], "admin") + ")");
        System.out.println("password:  ENC(" + CryptoUtils.encrypt(arr[0], "123456") + ")");
    }

    /**
     * 使用默认的数据源进行操作
     * http://localhost:8080/defaultDataSource
     */
    @GetMapping("defaultDataSource")
    public ResultData<List<Map<String, Object>>> defaultDataSource() {
        List<Map<String, Object>> mapList = jmdService.defaultDataSource();
        return ResultData.success(mapList);
    }

    /**
     * http://localhost:8080/testTransactional
     *
     * @return
     */
    @GetMapping("testTransactional")
    public ResultData<List<Map<String, Object>>> testTransactional() {
        jmdService.testTransactional();
        return ResultData.success();
    }

    /**
     * 使用 workflow 数据源进行操作
     * http://localhost:8080/workflowDataSource
     *
     * @param
     */
    @GetMapping("workflowDataSource")
    public ResultData<List<Map<String, Object>>> workflowDataSource() {
        List<Map<String, Object>> mapList = jmdService.workflowDataSource();
        return ResultData.success(mapList);
    }

    /**
     * 使用 element 数据源进行操作
     * http://localhost:8080/elementDataSource
     *
     * @param
     */
    @GetMapping("elementDataSource")
    public ResultData<List<Map<String, Object>>> elementDataSource() {
        List<Map<String, Object>> mapList = jmdService.elementDataSource();
        return ResultData.success(mapList);
    }

    /**
     * http://localhost:8080/jmd/h2FrameInit
     *
     * @return
     */
    @GetMapping("jmd/h2FrameInit")
    public ResultData<List<Map<String, Object>>> h2FrameInit() {
        jmdService.h2FrameInit();
        List<Map<String, Object>> mapList = jmdService.h2FrameLoadAllUser();
        return ResultData.success(mapList);
    }

    /**
     * http://localhost:8080/jmd/h2FrameLoadAllUser
     *
     * @return
     */
    @GetMapping("jmd/h2FrameLoadAllUser")
    public ResultData<List<Map<String, Object>>> h2FrameLoadAllUser() {
        List<Map<String, Object>> mapList = jmdService.h2FrameLoadAllUser();
        return ResultData.success(mapList);
    }

    /**
     * http://localhost:8080/jmd/sqlitePmInit
     *
     * @return
     */
    @GetMapping("jmd/sqlitePmInit")
    public ResultData<List<Map<String, Object>>> sqlitePmInit() {
        jmdService.sqlitePmInit();
        List<Map<String, Object>> mapList = jmdService.sqlitePmLoadAllUser();
        return ResultData.success(mapList);
    }

}
