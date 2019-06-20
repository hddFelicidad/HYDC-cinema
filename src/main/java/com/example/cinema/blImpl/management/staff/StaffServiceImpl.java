package com.example.cinema.blImpl.management.staff;

import com.example.cinema.bl.management.StaffService;
import com.example.cinema.blImpl.user.AccountServiceForBl;
import com.example.cinema.data.management.StaffMapper;
import com.example.cinema.po.Staff;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.StaffForm;
import com.example.cinema.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private AccountServiceForBl accountServiceForBl;


    @Override
    public ResponseVO addStaff(StaffForm staffForm){
        try {
            staffMapper.insertOneStaff(staffForm);
            accountServiceForBl.createNewAccount(staffForm.getUserName(),staffForm.getPassword(),staffForm.getPosition());
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateStaff(StaffForm staffForm){
        try {
            staffMapper.updateStaff(staffForm);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAllStaff(){
        try {
            List<StaffVO> staffVOList=staffList2StaffVOList(staffMapper.selectAllStaff());
            return ResponseVO.buildSuccess(staffVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getStaffInfo(int staffId){
        try {
            Staff staff=staffMapper.selectStaffById(staffId);
            StaffVO staffVO=staff.getVO();
            return ResponseVO.buildSuccess(staffVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deleteStaff(int staffId){
        try {
            staffMapper.deleteStaff(staffId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private List<StaffVO> staffList2StaffVOList(List<Staff> staffList){
        List<StaffVO> staffVOList=new ArrayList<>();
        for (Staff staff:staffList){
            StaffVO staffVO=staff.getVO();
            staffVOList.add(staffVO);
        }
        return staffVOList;
    }
}

