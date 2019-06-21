package com.example.cinema.blImpl.sales.order;

import com.example.cinema.bl.sales.OrderService;
import com.example.cinema.bl.sales.RechargeService;
import com.example.cinema.bl.sales.RefundStrategyService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.vip.VIPServiceForBl;
import com.example.cinema.blImpl.sales.ticket.TicketServiceForBl;
import com.example.cinema.data.sales.OrderMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService,OrderServiceForBl {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RechargeService rechargeService;
    @Autowired
    RefundStrategyService refundStrategyService;
    @Autowired
    ScheduleServiceForBl scheduleServiceForBl;
    @Autowired
    TicketServiceForBl ticketServiceForBl;
    @Autowired
    VIPServiceForBl vipServiceForBl;

    @Override
    public ResponseVO getConsumptionRecord(int userId){
        try {
            List<OrderVO> orderVOList=new ArrayList<>();
            List<Order> orderList1=orderMapper.selectOrdersByUserIdWithoutGiftCoupon(userId);
            List<Order> orderList=new ArrayList<>();
            for(Order order:orderList1){
                Order temp=orderMapper.selectOrderById(order.getId());
                if(temp==null){
                    orderList.add(order);
                }
                else{
                    orderList.add(temp);
                }
            }
            for (Order order:orderList){
                OrderVO orderVO=order.getVO();
                orderVOList.add(orderVO);
            }
            return ResponseVO.buildSuccess(orderVOList);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    @Override
    public ResponseVO getConsumptionRecordDetail(int orderId){
        try {
            Order order=orderMapper.selectOrderById(orderId);
            if(order==null){
                order=orderMapper.selectOrderByIdWithoutGiftCoupon(orderId);
            }
            OrderVO orderVO=order.getVO();
            List<Ticket> tickets=order.getTickets();
            ScheduleItem scheduleItem=scheduleServiceForBl.getScheduleItemById(tickets.get(0).getScheduleId());
            if(refundStrategyService.selectRefundStrategyByMovieId(scheduleItem.getMovieId())!=null) {
                RefundStrategy refundStrategy = refundStrategyService.selectRefundStrategyByMovieId(scheduleItem.getMovieId());
                int limitedTime = refundStrategy.getLimitedTime();
                Date dateNow = new Date();
                Date startDate = scheduleItem.getStartTime();
                Date limitedDate = getNumHourAfterDate(startDate, 0 - limitedTime);
                if (dateNow.after(limitedDate)&&order.getState()!=3&&order.getState()!=0) {
                    orderVO.setState("已完成但不可退票");
                }else if(order.getState()==0){
                    orderVO.setState("待支付");
                }else if(order.getState()==3){
                    orderVO.setState("退款成功");
                }
                else{
                    orderVO.setState("已完成且可退票");
                }
            }
            else{
                if(order.getState()==0){
                    orderVO.setState("待支付");
                }
                else{
                    orderVO.setState("已完成但不可退票");
                }
            }
            return ResponseVO.buildSuccess(orderVO);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getRechargeRecord(int userId){
        try {
            List<Recharge> rechargeList=rechargeService.getRechargeRecord(userId);
            List<RechargeVO> rechargeVOList=new ArrayList<>();
            for (Recharge recharge:rechargeList){
                RechargeVO rechargeVO=recharge.getVO();
                rechargeVOList.add(rechargeVO);
            }
            return ResponseVO.buildSuccess(rechargeVOList);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getRefundStrategyByOrderId(int orderId){
        try {
            Order order=orderMapper.selectOrderById(orderId);
            if(order==null){
                order=orderMapper.selectOrderByIdWithoutGiftCoupon(orderId);
            }
            List<Ticket> tickets=order.getTickets();
            ScheduleItem scheduleItem=scheduleServiceForBl.getScheduleItemById(tickets.get(0).getScheduleId());
            RefundStrategy refundStrategy= refundStrategyService.selectRefundStrategyByMovieId(scheduleItem.getMovieId());
            double servicePrice=(double)Math.round((refundStrategy.getBookingCharge())*(order.getRealMoney()))/100;
            RefundVO refundVO=new RefundVO();
            refundVO.setOriginalPrice(order.getRealMoney());
            refundVO.setServicePrice(servicePrice);
            refundVO.setRefundPrice(sub(servicePrice,order.getRealMoney()));
            return ResponseVO.buildSuccess(refundVO);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO cancelOrder(int orderId, RefundForm refundForm){
        try {
            Order order=orderMapper.selectOrderById(orderId);
            if(order==null){
                order=orderMapper.selectOrderByIdWithoutGiftCoupon(orderId);
            }
            List<Ticket> tickets=order.getTickets();
            ScheduleItem scheduleItem=scheduleServiceForBl.getScheduleItemById(tickets.get(0).getScheduleId());
            if(refundStrategyService.selectRefundStrategyByMovieId(scheduleItem.getMovieId())==null){
                //退款给会员卡
                if(order.getVIPCardId()!=-1){
                    VIPCard vipCard=vipServiceForBl.selectCardById(order.getVIPCardId());
                    double oldBalance=vipCard.getBalance();
                    double newBalance=oldBalance+order.getRealMoney();
                    vipServiceForBl.updateCardBalance(order.getVIPCardId(),newBalance);
                }

                return ResponseVO.buildSuccess("无退票策略，退票失败");
            }
            RefundStrategy refundStrategy= refundStrategyService.selectRefundStrategyByMovieId(scheduleItem.getMovieId());
            double servicePriceNow=(double)Math.round((refundStrategy.getBookingCharge())*(order.getRealMoney()))/100;
            if(order.getState()==0 ){
                return ResponseVO.buildSuccess("订单尚未支付，无法退款");
            }
            //1，退款成功2，在限制时间内无法退款3，退票策略在退票过程被修改
            else if(order.getState()==1){
                int limitedTime=refundStrategy.getLimitedTime();
                if(servicePriceNow!=refundForm.getServicePrice()){
                    return ResponseVO.buildSuccess("错误：退票策略异常。退票期间退票策略改变，退票失败");
                }
                else{
                    Date dateNow=new Date();
                    Date startDate=scheduleItem.getStartTime();
                    Date limitedDate=getNumHourAfterDate(startDate,0-limitedTime);
                    if(dateNow.after(limitedDate)){
                        return ResponseVO.buildSuccess("已超过过可退票时间，退票失败");
                    }
                    else{
                        orderMapper.updateOrderState(orderId,3);
                        for(Ticket ticket:order.getTickets()) {
                            ticketServiceForBl.updateTicketState(ticket.getId(),2);
                        }
                        for(Coupon coupon:order.getGiftCoupons()) {
                            orderMapper.deleteCouponsForOrder3(coupon.getId());
                        }
                        //退款给会员卡
                        if(order.getVIPCardId()!=-1){
                            VIPCard vipCard=vipServiceForBl.selectCardById(order.getVIPCardId());
                            double oldBalance=vipCard.getBalance();
                            double newBalance=oldBalance+order.getRealMoney()-servicePriceNow;
                            vipServiceForBl.updateCardBalance(order.getVIPCardId(),newBalance);
                            double realTotal=vipCard.getTotalAmount()-order.getRealMoney();
                            vipServiceForBl.updateCardTotalAmount(order.getVIPCardId(),realTotal);
                        }
                        return ResponseVO.buildSuccess("退票成功");
                    }
                }
            }
            else if(order.getState()==2){
                return ResponseVO.buildSuccess("订单已失效，无法退款");
            }
            else if(order.getState()==3){
                return ResponseVO.buildSuccess("订单在此之前已退款，无法退款");
            }
            else{
                return ResponseVO.buildSuccess("未知错误");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public void insertOrder(Order order){
        orderMapper.insertOrder(order);
    }

    @Override
    public void insertOrderTicket(int orderId,int ticketId){
        orderMapper.insertOrderTicket(orderId,ticketId);
    }

    @Override
    public void updateOrder(Order order){
        orderMapper.updateOrder(order);
    }

    @Override
    public void insertOrderGiftCoupon(int orderId,int couponId){
        orderMapper.insertOrderGiftCoupon(orderId,couponId);
    }

    @Override
    public void updateOrderState(int orderId,int state){
        orderMapper.updateOrderState(orderId,state);
    }

    @Override
    public void cleanExpiredOrder(){
        orderMapper.cleanExpiredOrder();
    }

    @Override
    public Order selectOrderById(int orderId){
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public Order selectOrderByIdWithoutGiftCoupon(int orderId){
        return orderMapper.selectOrderByIdWithoutGiftCoupon(orderId);
    }

    /**
     * 获得num小时后的日期
     * @param oldDate
     * @param num
     * @return
     */
    private Date getNumHourAfterDate(Date oldDate, int num){
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.HOUR_OF_DAY, num);
        return calendarTime.getTime();
    }

    public double sub(double a,double b){
        BigDecimal a1=new BigDecimal(Double.toString(a));
        BigDecimal b1=new BigDecimal(Double.toString(b));
        return b1.subtract(a1).doubleValue();
    }

}
