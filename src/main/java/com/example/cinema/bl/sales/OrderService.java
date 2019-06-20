package com.example.cinema.bl.sales;

import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author bjh
 * @date 2019/6/5 10:00 AM
 */
public interface OrderService {
    /**
     * 获取消费记录
     */
    ResponseVO getConsumptionRecord(int userId);
    /**
     * 获取消费记录详情
     */
    ResponseVO getConsumptionRecordDetail(int orderId);
    /**
     * 获取充值记录
     */
    ResponseVO getRechargeRecord(int userId);
    /**
     * 通过订单id获取退票策略
     */
    ResponseVO getRefundStrategyByOrderId(int orderId);
    /**
     * 取消订单
     */
    ResponseVO cancelOrder(int orderId, RefundForm refundForm);
}
