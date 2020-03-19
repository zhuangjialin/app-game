package com.sdys.appgame.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sdys.appgame.entity.*;
import com.sdys.appgame.server.ConfigService;
import com.sdys.appgame.server.DailyVisitTrendService;
import com.sdys.appgame.utils.DateUtils;
import com.sdys.appgame.utils.MapObjectTransferUtil;
import com.sdys.appgame.utils.MathUtils;
import com.sdys.appgame.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dailyVisitTrend/contr")
public class DailyVisitTrendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyVisitTrendController.class);


    @Resource
    private DailyVisitTrendService dailyVisitTrendService;


    @Resource
    private ConfigService configService;




    @PostMapping("/find_daily_visit_trend")
    public Object findDailyVisitTrend(@RequestBody Map<String,Object> map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Integer pageSize = 10;
        Integer pageNo = StringUtils.isEmpty(map.get("pageNo")) ? 0:Integer.valueOf(map.get("pageNo").toString()) ;
        PageHelper.startPage(pageNo, pageSize);
        DailyVisitTrend dailyVisitTrend = (DailyVisitTrend) MapObjectTransferUtil.mapToObject(map,DailyVisitTrend.class);
        List<DailyVisitTrend> dailyVisitTrendList = dailyVisitTrendService.findByParams(dailyVisitTrend);
        PageInfo pageInfo = new PageInfo(dailyVisitTrendList);
        result.put("total",pageInfo.getTotal());
        result.put("rows",dailyVisitTrendList);
        result.put("pageNo",pageInfo.getPageNum());
        return new JsonResult(ResultCode.SUCCESS,dailyVisitTrendList);
    }


    @GetMapping("/insert_selective")
    private void insertSelective() throws Exception {
        Config configParam = new Config();
        List<Config> configList = configService.findConfigByVisit(configParam);
        for (Config config : configList) {
            String secret = config.getParam4();
            String appId = config.getAppId();
            String endDate =DateUtils.getDateBefore();
            String beginDate = DateUtils.getDateBefore();
            List<Map<String,Object>> list = WeChatUtil.getDailyVisitTrend(appId,secret,beginDate,endDate);
            if(list.size()>0){
                for (Map<String, Object> map : list) {
                    DailyVisitTrend dailyVisitTrend =new DailyVisitTrend();
                    dailyVisitTrend.setCreateTime(new Date());
                    dailyVisitTrend.setAppid(config.getAppId());
                    dailyVisitTrend.setConfigId(config.getId());
                    dailyVisitTrend.setRefDate(map.get("ref_date").toString());
                    dailyVisitTrend.setVisitPv((Integer) map.get("visit_pv"));
                    dailyVisitTrend.setSessionCnt((Integer) map.get("session_cnt"));
                    dailyVisitTrend.setStayTimeSession(MathUtils.getBigDecimal(map.get("stay_time_session")));
                    dailyVisitTrend.setVisitUv((Integer) map.get("visit_uv"));
                    dailyVisitTrend.setVisitUvNew((Integer) map.get("visit_uv_new"));
                    dailyVisitTrend.setStayTimeUv(MathUtils.getBigDecimal(map.get("stay_time_uv")));
                    dailyVisitTrend.setVisitDepth(MathUtils.getBigDecimal(map.get("visit_depth")));
                    if(dailyVisitTrendService.booleanIsExist(dailyVisitTrend)==0){
                        dailyVisitTrendService.insertSelective(dailyVisitTrend);
                    }
                }
            }
        }

    }

    @PostMapping("/find_daily_visit_trend_up")
    public Object findDailyVisitTrendUp(@RequestBody Map<String,String> map) throws Exception {
        LOGGER.info("map = {}",map.toString());
        if(!StringUtils.isEmpty(map.get("refDate"))){
            map.put("refDate",DateUtils.transferToDate(map.get("refDate"),""));
        }else{
            map.put("refDate",DateUtils.getDateBefore());
        }
        Map<String,Object> result = new HashMap<>();
        Integer pageNo = StringUtils.isEmpty(map.get("pageNo")) ? 0:Integer.valueOf(map.get("pageNo")) ;
        Integer pageSize = 10;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,String>> list = dailyVisitTrendService.findDailyVisitTrend(map);
        Map<String,String> allCount = dailyVisitTrendService.getAllCountData(map);
        PageInfo pageInfo = new PageInfo(list);
        result.put("total",pageInfo.getTotal());
        result.put("rows",list);
        result.put("allCount",allCount);
        result.put("pageNo",pageInfo.getPageNum());
        return result;
    }

}
