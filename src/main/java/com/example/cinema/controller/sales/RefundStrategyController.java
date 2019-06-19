package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.RefundStrategyService;
import com.example.cinema.vo.RefundStrategyForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**退票策略管理
 * @author poh
 * @date 2019/6/14 6:45 PM
 */
@RestController
@RequestMapping("/strategy")
public class RefundStrategyController {
    @Autowired
    RefundStrategyService refundStrategyService;


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseVO getAllRefundStrategy(){
        return refundStrategyService.getAllRefundStrategy();
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseVO getRefundStrategyById(@RequestParam int id){
        return refundStrategyService.getRefundStrategyById(id);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseVO addRefundStrategy(@RequestBody RefundStrategyForm refundStrategyForm){
        return refundStrategyService.addRefundStrategy(refundStrategyForm);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseVO updateRefundStrategy(@RequestBody RefundStrategyForm refundStrategyForm){
        return refundStrategyService.updateRefundStrategy(refundStrategyForm);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseVO deleteRefundStrategyById(@RequestParam int id){
        return refundStrategyService.deleteRefundStrategyById(id);
    }
}
