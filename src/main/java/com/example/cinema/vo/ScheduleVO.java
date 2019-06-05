package com.example.cinema.vo;


import java.util.Date;
import java.util.List;


public class ScheduleVO {
    /**
     * 日期
     */
    private Date date;
    /**
     * 排片信息列表
     */
    private List<ScheduleItemVO> scheduleItemList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ScheduleItemVO> getScheduleItemList() {
        return scheduleItemList;
    }

    public void setScheduleItemList(List<ScheduleItemVO> scheduleItemList) {
        this.scheduleItemList = scheduleItemList;
    }
}
