package com.example.cinema.data.sales;

import com.example.cinema.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * @author pooh
 * @date 2019/6/1 7:21 PM
 */
@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    int insertOrderTicket(@Param("orderId") int orderId, @Param("ticketId") int ticketId);

    int insertOrderGiftCoupon(@Param("orderId") int orderId, @Param("giftCouponId") int giftCouponId);

    void updateOrderState(@Param("orderId") int orderId, @Param("state") int state);

    int updateOrder(Order order);

    List<Order> selectOrdersByUserId(int userId);

    //List<Order> selectOrdersBeforeRefundableTime(Timestamp refundableTime);

    Order selectOrderById(int id);

    List<Order> selectOrdersByUserIdWithoutGiftCoupon(int userId);

    Order selectOrderByIdWithoutGiftCoupon(int id);

    /**
     * 删除订单里的票
     */
    int deleteTicketsForOrder(int orderId);
    int deleteTicketsForOrder2(int ticketId);

    /**
     * 删除订单里赠送的优惠券
     */
    int deleteCouponsForOrder(int orderId);
    int deleteCouponsForOrder2(int couponId);
    int deleteCouponsForOrder3(int couponId);

    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredOrder();
}
