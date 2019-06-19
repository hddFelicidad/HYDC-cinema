package com.example.cinema.data.promotion;

import com.example.cinema.po.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author poh
 * @date 2019/6/1 7:21 PM
 */
@Mapper
public interface ActivityMapper {
    /**
     * 插入一个活动
     * @param activity
     * @return
     */
    int insertActivity(Activity activity);
    /**
     * 插入一个活动与电影的关联
     * @param activityId
     * @param movieId
     * @return
     */
    int insertActivityAndMovie(@Param("activityId") int activityId,@Param("movieId") List<Integer> movieId);
    /**
     * 获取所有活动
     * @return
     */
    List<Activity> selectActivities();
    /**
     * 根据电影获取活动
     * @param movieId
     * @return
     */
    List<Activity> selectActivitiesByMovie(int movieId);
    /**
     * 根据活动id获取活动
     * @param id
     * @return
     */
    Activity selectById(int id);

    List<Activity> selectActivitiesWithoutMovie();






}
