package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.RechargeService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**用户充值记录管理
 * @author bjh
 * @date 2019/6/2 10:13 AM
 */
@RestController
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    RechargeService rechargeService;

    @RequestMapping(value = "/getRecharge",method = RequestMethod.GET)
    public ResponseVO getRecord(@RequestParam int userId){
        return rechargeService.getRecord(userId);
    }
}
