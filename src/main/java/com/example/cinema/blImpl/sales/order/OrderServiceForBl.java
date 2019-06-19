package com.example.cinema.blImpl.sales.order;

import com.example.cinema.po.Order;

public interface OrderServiceForBl {

    /**
     * 插入新订单
     * @param order
     */
    void insertOrder(Order order);

    /**
     * 插入订单和电影票关联信息
     * @param orderId
     * @param ticketId
     */
    void insertOrderTicket(int orderId,int ticketId);

    /**
     * 更新订单信息
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 插入订单和优惠券关联信息
     * @param orderId
     * @param couponId
     */
    void insertOrderGiftCoupon(int orderId,int couponId);

    /**
     * 更新订单状态
     * @param orderId
     * @param state
     */
    void updateOrderState(int orderId,int state);

    /**
     * 定时
     */
    void cleanExpiredOrder();

    /**
     * 根据id选择订单
     * @param orderId
     * @return
     */
    Order selectOrderById(int orderId);

    /**
     * 查看没有赠送优惠券的order
     * @param orderId
     * @return
     */
    Order selectOrderByIdWithoutGiftCoupon(int orderId);
}
