package com.example.cinema.blImpl.management.schedule;

import com.example.cinema.po.ScheduleItem;

import java.util.Date;
import java.util.List;


public interface ScheduleServiceForBl {
    /**
     * 查询所有涉及到movieIdList中电影的排片信息
     * @param movieIdList
     * @return
     */
    List<ScheduleItem> getScheduleByMovieIdList(List<Integer> movieIdList);

    /**
     * 根据id查找排片信息
     * @param id
     * @return
     */
    ScheduleItem getScheduleItemById(int id);

    /**
     * 根据id，开始结束日期查找拍片
     * @param hallId
     * @param startDate
     * @param endDate
     * @return
     */
    List<ScheduleItem> selectSchedule(int hallId, Date startDate, Date endDate);

    /**
     * 获取排片率
     * @param movieId
     * @param date
     * @param afterDate
     * @return
     */
    List<ScheduleItem> selectScheduleByMovieIdAndDate(int movieId,Date date,Date afterDate);
}
