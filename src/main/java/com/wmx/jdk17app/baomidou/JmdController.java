package com.wmx.jdk17app.baomidou;

import com.wmx.jdk17app.pojo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Springboot + JdbcTemplate + hikari 多数据源使用
 * 注入同一类型全部实现类的实例
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
