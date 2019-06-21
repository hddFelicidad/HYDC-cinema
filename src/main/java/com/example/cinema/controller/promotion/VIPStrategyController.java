package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPStrategyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**会员卡策略管理
 * @author poh
 * @date 2019/6/9 8：30 PM
 */
@RestController
@RequestMapping("/vipStrategy")
public class VIPStrategyController {
    @Autowired
    VIPStrategyService vipStrategyService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseVO publishVIPStrategy(@RequestBody VIPStrategyForm vipStrategyForm){
        return vipStrategyService.publishVIPStrategy(vipStrategyForm);
    }
    @RequestMapping(value = "/takeoff", method = RequestMethod.POST)
    public ResponseVO takeoffVIPStrategy(@RequestBody VIPStrategyForm vipStrategyForm){
        return vipStrategyService.takeoffVIPStrategy(vipStrategyForm);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseVO updateVIPStrategy(@RequestBody VIPStrategyForm vipStrategyForm){
        return vipStrategyService.updateVIPStrategy(vipStrategyForm);
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseVO getVIPStrategy(){
        return vipStrategyService.getVIPStrategy();
    }

}
