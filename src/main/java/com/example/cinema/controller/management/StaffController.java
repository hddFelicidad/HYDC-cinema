package com.example.cinema.controller.management;

import com.example.cinema.bl.management.StaffService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.StaffForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**员工管理
 * @author poh
 * @date 2019/6/11 6:00 PM
 */
@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/staff/add", method = RequestMethod.POST)
    public ResponseVO addStaff(@RequestBody StaffForm staffForm){
        return staffService.addStaff(staffForm);
    }

    @RequestMapping(value = "/staff/update", method = RequestMethod.POST)
    public ResponseVO updateStaff(@RequestBody StaffForm staffForm){
        return staffService.updateStaff(staffForm);
    }
    @RequestMapping(value = "/staff/all", method = RequestMethod.GET)
    public ResponseVO getAllStaff(){
        return staffService.getAllStaff();
    }


    @RequestMapping(value = "/staff/info", method = RequestMethod.GET)
    public ResponseVO getStaffInfo(@RequestParam int staffId){
        return staffService.getStaffInfo(staffId);
    }

    @RequestMapping(value = "/staff/delete", method = RequestMethod.DELETE)
    public ResponseVO deleteStaff(@RequestParam int staffId){
        return staffService.deleteStaff(staffId);
    }
}

