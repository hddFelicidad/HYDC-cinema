package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author pooh
 * @date 2019/5/21 8:00 PM
 */
public interface ActivityService {

    /**
     * 发布活动
     * @param activityForm
     * @return
     */
    ResponseVO publishActivity(ActivityForm activityForm);

    /**
     * 获取所所有活动信息
     * @return
     */
    ResponseVO getActivities();




}
