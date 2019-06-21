package com.example.cinema.data.management;

import com.example.cinema.po.Staff;
import com.example.cinema.vo.StaffForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pooh
 * @date 2019/5/11 5:00 PM
 */
@Mapper
public interface StaffMapper {
    /**
     * 插入一个影院员工
     * @param staffForm
     * @return
     */
    int insertOneStaff(StaffForm staffForm);
    /**
     * 查询所有影院员工
     * @return
     */
    List<Staff> selectAllStaff();
    /**
     * 根据员工id查询影院员工
     * @param staffId
     * @return
     */
    Staff selectStaffById(@Param("staffId") int staffId);
    /**
     * 更新影院员工信息
     * @param staffForm
     * @return
     */
    int updateStaff(StaffForm staffForm);
    /**
     * 根据员工id删除影院员工
     * @param staffId
     * @return
     */
    int deleteStaff(int staffId);
}
