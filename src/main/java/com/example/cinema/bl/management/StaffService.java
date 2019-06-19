package com.example.cinema.bl.management;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.StaffForm;

/**
 * @author pooh
 * @date 2019/6/11 8:00 PM
 */
public interface StaffService {

    /**
     * 增加新员工
     * @param staffForm
     * @return
     */
    ResponseVO addStaff(StaffForm staffForm);

    /**
     * 更新员工信息
     * @param staffForm
     * @return
     */
    ResponseVO updateStaff(StaffForm staffForm);

    /**
     * 获取所有员工信息
     * @return
     */
    ResponseVO getAllStaff();

    /**
     * 通过id获取员工信息
     * @param staffId
     * @return
     */
    ResponseVO getStaffInfo(int staffId);

    ResponseVO deleteStaff(int staffId);
}
