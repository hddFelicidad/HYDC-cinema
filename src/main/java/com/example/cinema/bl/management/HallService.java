package com.example.cinema.bl.management;

import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author pooh
 * @date 2019/6/3 4:00 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     * @return
     */
    ResponseVO searchAllHall();

    /**
     * 通过Id搜索影厅
     * @param id
     * @return
     */
    ResponseVO selectHallById(int id);

    /**
     * 新增影厅
     * @param hallForm
     * @return
     */
    ResponseVO addHall(HallForm hallForm);

    /**
     * 更新影厅（修改影厅信息）
     * @param hallForm
     * @return
     */
    ResponseVO updateHall(HallForm hallForm);

    /**
     * 删除影厅
     * @param id
     * @return
     */
    ResponseVO deleteHall(int id);

}
