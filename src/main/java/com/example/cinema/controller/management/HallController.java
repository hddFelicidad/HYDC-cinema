package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

/**影厅管理
 * @author fjj
 * @date 2019/4/12 1:59 PM
 */
@RestController
public class HallController {
    @Autowired
    private HallService hallService;

    @RequestMapping(value = "hall/all", method = RequestMethod.GET)
    public ResponseVO searchAllHall(){
        return hallService.searchAllHall();
    }

    @RequestMapping(value="hall/get", method = RequestMethod.GET)
    public ResponseVO selectHallById(@RequestParam int id) {
        return hallService.selectHallById(id);}

    @RequestMapping(value = "hall/add", method = RequestMethod.POST)
    public ResponseVO addHall(@RequestBody HallForm hallForm) {
        return hallService.addHall(hallForm);}

    @RequestMapping(value = "hall/update", method = RequestMethod.POST)
    public ResponseVO updateHall(@RequestBody HallForm hallForm) {
        return hallService.updateHall(hallForm);}

    @RequestMapping(value = "hall/delete", method = RequestMethod.DELETE)
    public ResponseVO deleteHallById(@RequestParam int id) {
        return hallService.deleteHall(id);}
}

