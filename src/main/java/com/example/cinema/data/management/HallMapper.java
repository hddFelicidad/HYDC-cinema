package com.example.cinema.data.management;

import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pooh
 * @date 2019/6/12 5:30 PM
 */
@Mapper
public interface HallMapper {
    /**
     * 查询所有影厅信息
     * @return
     */
    List<Hall> selectAllHall();

    /**
     * 根据id查询影厅
     * @return
     */
    Hall selectHallById(@Param("hallId") int hallId);

    /**
     * 插入一条电影信息
     * @param addHallForm
     * @return
     */
    int insertOneHall(HallForm addHallForm);

    /**
     * 修改电影
     * @param updateHallForm
     * @return
     */
    int updateHall(HallForm updateHallForm);

    /**
     * 删除影厅
     * @param hallId
     * @return
     */
    int deleteHall(int hallId);

}
