package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.OrderService;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.RefundVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**用户订单管理
 * @author bjh
 * @date 2019/6/10 5:20 PM
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/getConsumption",method = RequestMethod.GET)
    public ResponseVO getConsumptionRecord(@RequestParam int userId){
        return orderService.getConsumptionRecord(userId);
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ResponseVO getConsumptionRecordDetail(@RequestParam int orderId){
        return orderService.getConsumptionRecordDetail(orderId);
    }

    @RequestMapping(value = "/getRecharge",method = RequestMethod.GET)
    public ResponseVO getRechargeRecord(@RequestParam int userId){
        return orderService.getRechargeRecord(userId);
    }

    @RequestMapping(value="/getStrategy", method = RequestMethod.GET)
    public ResponseVO getRefundStrategyByOrderId(@RequestParam int orderId){
        return orderService.getRefundStrategyByOrderId(orderId);
    }

    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public ResponseVO cancelOrder(@RequestParam int orderId, @RequestBody RefundForm refundForm){
        return orderService.cancelOrder(orderId,refundForm);

    }}
