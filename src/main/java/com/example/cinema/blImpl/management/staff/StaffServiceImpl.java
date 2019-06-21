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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    private static final String TIME_CONFLICT_ERROR_MESSAGE="用户出生日期不应晚于当前时间";
    private static final String NAME_LENGTH_ERROR_MESSAGE="用户姓名长度不符合要求";
    private static final String USERNAME_LENGTH_ERROR_MESSAGE="用户名长度不符合要求";
    private static final String PASSWORD_LENGTH_ERROR_MESSAGE="用户密码长度不符合要求";
    private static final String ID_CARD_LENGTH_ERROR_MESSAGE="身份证号码长度不符合要求";

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private AccountServiceForBl accountServiceForBl;


    @Override
    public ResponseVO addStaff(StaffForm staffForm){
        try {
            if (checkStaffInfo(staffForm)!=null){
                return ResponseVO.buildFailure(checkStaffInfo(staffForm));
            }
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
            if (checkStaffInfo(staffForm)!=null){
                return ResponseVO.buildFailure(checkStaffInfo(staffForm));
            }
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

    private String checkStaffInfo(StaffForm staffForm){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        if (staffForm.getName().length()>6||staffForm.getName().length()<2){
            return NAME_LENGTH_ERROR_MESSAGE;
        }
        else if (staffForm.getIdnumber().length()!=18){
            return ID_CARD_LENGTH_ERROR_MESSAGE;

        }
        else if ((staffForm.getUserName().length()<4&&staffForm.getUserName().length()>0)||staffForm.getUserName().length()>10){
            return USERNAME_LENGTH_ERROR_MESSAGE;
        }
        else if ((staffForm.getPassword().length()<6&&staffForm.getPassword().length()>0)||staffForm.getPassword().length()>12){
            return PASSWORD_LENGTH_ERROR_MESSAGE;
        }
        else if (d.before(staffForm.getBirth())){
            return TIME_CONFLICT_ERROR_MESSAGE;
        }
        return null;
    }
}

