package com.sdys.appgame.task;

import com.sdys.appgame.entity.Config;
import com.sdys.appgame.entity.DailyVisitTrend;
import com.sdys.appgame.server.ConfigService;
import com.sdys.appgame.server.DailyVisitTrendService;
import com.sdys.appgame.utils.DateUtils;
import com.sdys.appgame.utils.MathUtils;
import com.sdys.appgame.utils.WeChatUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    //3.添加定时任务



    @Resource
    private DailyVisitTrendService dailyVisitTrendService;


    @Resource
    private ConfigService configService;






    //或直接指定时间间隔，例如：5秒

    //@Scheduled(fixedRate=5000)
    @Scheduled(cron = "5 30 0 * * ?")
    private void configureTasks() {
        Config configParam = new Config();
        List<Config> configList = configService.findConfigByVisit(configParam);
        if(configList.size()>0){
            for (Config config : configList) {
                String appId = config.getAppId();
                String secret = config.getParam4();
                String beginDate = DateUtils.getDateBefore();
                String endDate = DateUtils.getDateBefore();
                List<Map<String, Object>> list = WeChatUtil.getDailyVisitTrend(appId, secret, beginDate, endDate);
                if (list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        DailyVisitTrend dailyVisitTrend = new DailyVisitTrend();
                        dailyVisitTrend.setRefDate(map.get("ref_date").toString());
                        dailyVisitTrend.setSessionCnt((Integer) map.get("session_cnt"));
                        dailyVisitTrend.setVisitPv((Integer) map.get("visit_pv"));
                        dailyVisitTrend.setVisitUv((Integer) map.get("visit_uv"));
                        dailyVisitTrend.setVisitUvNew((Integer) map.get("visit_uv_new"));
                        dailyVisitTrend.setStayTimeUv(MathUtils.getBigDecimal(map.get("stay_time_uv")));
                        dailyVisitTrend.setStayTimeSession(MathUtils.getBigDecimal(map.get("stay_time_session")));
                        dailyVisitTrend.setVisitDepth(MathUtils.getBigDecimal(map.get("visit_depth")));
                        dailyVisitTrend.setCreateTime(new Date());
                        dailyVisitTrend.setAppid(config.getAppId());
                        dailyVisitTrend.setConfigId(config.getId());
                        if (dailyVisitTrendService.booleanIsExist(dailyVisitTrend) == 0) {
                            dailyVisitTrendService.insertSelective(dailyVisitTrend);
                        }
                    }
                }
            }
        }
    }
}