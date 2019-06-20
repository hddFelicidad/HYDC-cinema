package com.example.cinema.blImpl.sales.ticket;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.activity.ActivityServiceForBl;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.blImpl.promotion.vip.VIPServiceForBl;
import com.example.cinema.blImpl.sales.order.OrderServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService,TicketServiceForBl {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    CouponService couponService;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallServiceForBl;
    @Autowired
    CouponServiceForBl couponServiceForBl;
    @Autowired
    ActivityServiceForBl activityServiceForBl;
    @Autowired
    VIPServiceForBl vipServiceForBl;
    @Autowired
    ScheduleServiceForBl scheduleServiceForBl;
    @Autowired
    MovieServiceForBl movieServiceForBl;
    @Autowired
    OrderServiceForBl orderServiceForBl;

    public final static Order order=new Order();
    @Override
    @Transactional
    /** SomeQuestionForaddTicket()
     *  buildSuccess(TicketWithCouponVO)
     */
    public ResponseVO addTicket(TicketForm ticketForm) {
        try{
            Timestamp d = new Timestamp(System.currentTimeMillis());//获取当前时间
            List<SeatForm> seats=ticketForm.getSeats();
            List<Ticket> ticketList=new ArrayList<>();
            for(SeatForm seat : seats){
                //自动生成id（数据库）
                Ticket ticket=new Ticket();
                ticket.setUserId(ticketForm.getUserId());
                ticket.setScheduleId(ticketForm.getScheduleId());
                ticket.setColumnIndex(seat.getColumnIndex());
                ticket.setRowIndex(seat.getRowIndex());
                ticket.setState(0);
                ticket.setTime(d);
                //ticketMapper.cleanExpiredTicket();//（MySQL、timediff）需修改(TicketMapper.xml)
                //应该用在complete里面，所调用为ticket里面的timestamp（time变量），然后比较当前时间now()
                ticketList.add(ticket);
            }
            ticketMapper.insertTickets(ticketList);
            //计算出电影票总价
            double totalPrice=0;
            for(Ticket tempTicket : ticketList){
                int scheduleId=tempTicket.getScheduleId();
                TicketWithScheduleVO ticketWithScheduleVO=tempTicket.getWithScheduleVO();
                ticketWithScheduleVO.setSchedule(scheduleServiceForBl.getScheduleItemById(scheduleId));
                ScheduleItem scheduleItem=ticketWithScheduleVO.getSchedule();
                double ticketPrice=scheduleItem.getFare();
                totalPrice+=ticketPrice;
            }
            //选择最优优惠方案
            List<Coupon> couponList= couponServiceForBl.getCouponListByUserId(ticketForm.getUserId());
            List<Coupon> usableCouponList = new ArrayList<>();
            if (couponList==null){
                Coupon coupon=new Coupon();
                coupon.setDiscountAmount(0);
                coupon.setTargetAmount(0);
                coupon.setId(0);
                usableCouponList.add(coupon);
            }
            //判断用户所拥有的优惠劵的可使用性
            else {
                for (Coupon coupon : couponList) {
                    double targetMoney = coupon.getTargetAmount();
                    if (targetMoney <= totalPrice) {
                        usableCouponList.add(coupon);
                    }
                }
            }
            TicketWithCouponVO ticketWithCouponVO=new TicketWithCouponVO();
            ticketWithCouponVO.setCoupons(usableCouponList);
            ticketWithCouponVO.setActivities(activityServiceForBl.getActivitiesByMovie(ticketList.get(0).getId()));
            List<TicketVO> ticketVOList=new ArrayList<>();
            for(Ticket t : ticketList){
                ticketVOList.add(t.getVO());
            }
            ticketWithCouponVO.setTicketVOList(ticketVOList);
            ticketWithCouponVO.setTotal(totalPrice);
            //添加新order
            order.setUserId(ticketForm.getUserId());
            order.setState(0);
            order.setTickets(ticketList);
            //添加所有的座位号
            StringBuffer s=new StringBuffer();
            int scheduleId=ticketList.get(0).getScheduleId();
            ScheduleItem scheduleItem=scheduleServiceForBl.getScheduleItemById(scheduleId);
            String hallName=scheduleItem.getHallName();
            s.append(hallName);
            for (Ticket ticket:ticketList){
                String seatNumber=(ticket.getRowIndex()+1)+"排"+(ticket.getColumnIndex()+1)+"座";
                s.append(" ");
                s.append(seatNumber);
            }
            String result=new String(s);
            order.setSeats(result);
            order.setMovieName(scheduleItem.getMovieName());
            int movieId=scheduleItem.getMovieId();
            Movie movie=movieServiceForBl.getMovieById(movieId);
            String url=movie.getPosterUrl();
            order.setPosterUrl(url);
            order.setScheduleTime(scheduleItem.getStartTime());
            order.setOriginMoney(totalPrice);
            order.setRealMoney(totalPrice);
            //将-1(会员卡不存在)存入Order
            order.setVIPCardId(-1);
            orderServiceForBl.insertOrder(order);
            for (Ticket ticket:ticketList){
                orderServiceForBl.insertOrderTicket(order.getId(),ticket.getId());
            }
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getSelectedCoupon(int couponId){
        try {
            if (couponId!=0){
                Coupon coupon=couponServiceForBl.getCouponById(couponId);
                double discountMoney=coupon.getDiscountAmount();
                double originMoney=order.getOriginMoney();
                double realMoney=sub(discountMoney,originMoney);
                if (realMoney<0) {
                    realMoney = 0;
                }
                order.setRealMoney(realMoney);
                order.setUsedCoupon(coupon);
                orderServiceForBl.updateOrder(order);
            }
            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        try{
            if (buyTicket(id).equals("票已超时")){
                return ResponseVO.buildFailure("票已超时");
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
            Hall hall=hallServiceForBl.getHallById(schedule.getHallId());
            int[][] seats=new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try{
            List<Ticket> listTicket=ticketMapper.selectTicketByUser(userId);
            List<TicketWithScheduleVO> listTicketWithScheduleVO=new ArrayList<>();
            for(Ticket ticket : listTicket){
                TicketVO ticketVO=ticket.getVO();
                TicketWithScheduleVO temp=new TicketWithScheduleVO();
                temp.setId(ticketVO.getId());
                temp.setUserId(ticketVO.getUserId());
                ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticketVO.getScheduleId());
                temp.setSchedule(scheduleItem);
                temp.setColumnIndex(ticketVO.getColumnIndex());
                temp.setRowIndex(ticketVO.getRowIndex());
                temp.setState(ticketVO.getState());
                temp.setTime(ticketVO.getTime());
                if(!temp.getState().equals("未完成")) {
                    listTicketWithScheduleVO.add(temp);
                }
            }
            return ResponseVO.buildSuccess(listTicketWithScheduleVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        try {
            double discount=0;
            if (buyTicket(id).equals("票已超时")){
                return ResponseVO.buildFailure("票已超时");
            }
            //将会员卡id存入Order
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            int UserId = temp.getUserId();
            VIPCard vipCard = vipServiceForBl.getVIPCardByUserId(UserId);
            //获取会员卡信息
            if (vipCard == null) {
                return ResponseVO.buildFailure("VIP卡不存在");
            } else {
                order.setVIPCardId(vipCard.getId());
                //更新会员卡信息
                double newBalance = 0;
                //计算电影票总价
                double totalPrice=order.getOriginMoney();
                if (order.getUsedCoupon()!=null){
                    discount=order.getUsedCoupon().getDiscountAmount();
                }
                newBalance = vipCard.getBalance() - (totalPrice - discount);
                if (newBalance < 0) {
                    for(Integer a :id) {
                        ticketMapper.updateTicketState(a, 0);
                    }
                    orderServiceForBl.updateOrderState(order.getId(),0);
                    return ResponseVO.buildFailure("余额不足");
                } else {
                    vipCard.setBalance(newBalance);
                    //更新总消费金额和订单时间
                    vipServiceForBl.updateCardTotalAmount(vipCard.getId(),vipCard.getTotalAmount()+order.getRealMoney());
                    Timestamp tempTime = new Timestamp(System.currentTimeMillis());
                    vipServiceForBl.updateCardLastOrderDate(vipCard.getId(),tempTime);

                    vipServiceForBl.updateCardBalance(vipCard.getId(), newBalance);
                    VIPCardVO vipCardVO = vipCard.toVO();
                    return ResponseVO.buildSuccess(vipCardVO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try{
            for (Integer a:id){
                Ticket ticket=ticketMapper.selectTicketById(a);
                if (ticket!=null){
                    ticketMapper.deleteTicket(a);
                }
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO repay(int orderId){
        try {
            Order order=orderServiceForBl.selectOrderById(orderId);
            if (order==null){
                order=orderServiceForBl.selectOrderByIdWithoutGiftCoupon(orderId);
            }
            List<Ticket> tickets=order.getTickets();
            List<Integer> ticketsId=new ArrayList<>();
            for (Ticket ticket:tickets){
                ticketsId.add(ticket.getId());
            }
            if (buyTicket(ticketsId).equals("票已超时")){
                return ResponseVO.buildFailure("票已超时");
            }
            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO repayByVIPCard(int orderId){
        try {
            double discount=0;
            Order order=orderServiceForBl.selectOrderById(orderId);
            if (order==null){
                order=orderServiceForBl.selectOrderByIdWithoutGiftCoupon(orderId);
            }
            List<Ticket> tickets=order.getTickets();
            List<Integer> ticketsId=new ArrayList<>();
            for (Ticket ticket:tickets){
                ticketsId.add(ticket.getId());
            }
            if (buyTicket(ticketsId).equals("票已超时")){
                return ResponseVO.buildFailure("票已超时");
            }
            //将会员卡id存入Order
            Ticket temp =tickets.get(0);
            int UserId = temp.getUserId();
            VIPCard vipCard = vipServiceForBl.getVIPCardByUserId(UserId);
            //获取会员卡信息
            if (vipCard == null) {
                return ResponseVO.buildFailure("VIP卡不存在");
            } else {
                order.setVIPCardId(vipCard.getId());
                //更新会员卡信息
                double newBalance = 0;
                //计算电影票总价
                double totalPrice=order.getOriginMoney();
                if (order.getUsedCoupon()!=null){
                    discount=order.getUsedCoupon().getDiscountAmount();
                }
                newBalance = vipCard.getBalance() - (totalPrice - discount);
                if (newBalance < 0) {
                    for(Ticket ticket:tickets) {
                        int ticketId=ticket.getId();
                        ticketMapper.updateTicketState(ticketId, 0);
                    }
                    orderServiceForBl.updateOrderState(order.getId(),0);
                    return ResponseVO.buildFailure("余额不足");
                } else {
                    vipServiceForBl.updateCardTotalAmount(vipCard.getId(),vipCard.getTotalAmount()+order.getRealMoney());
                    Timestamp tempTime = new Timestamp(System.currentTimeMillis());
                    vipServiceForBl.updateCardLastOrderDate(vipCard.getId(),tempTime);

                    vipCard.setBalance(newBalance);
                    vipServiceForBl.updateCardBalance(vipCard.getId(), newBalance);
                    VIPCardVO vipCardVO = vipCard.toVO();
                    return ResponseVO.buildSuccess(vipCardVO);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public void updateTicketState(int id,int state){
        ticketMapper.updateTicketState(id,state);
    }

    @Override
    public List<Ticket> selectTicketsBySchedule(int scheduleId){
        return ticketMapper.selectTicketsBySchedule(scheduleId);
    }

    private String buyTicket(List<Integer> id){
        int userId=0;
        int movieId=0;
        for (Integer a:id){
            Ticket ticket=ticketMapper.selectTicketById(a);
            ticketMapper.cleanExpiredTicket();
            userId=ticket.getUserId();
            if (ticket.getState()==0){
                ticketMapper.updateTicketState(a,1);
            }
            else if (ticket.getState()==2){
                return "票已超时";
            }
            int scheduleId=ticket.getScheduleId();
            TicketWithScheduleVO ticketWithScheduleVO=ticket.getWithScheduleVO();
            ticketWithScheduleVO.setSchedule(scheduleServiceForBl.getScheduleItemById(scheduleId));
            ScheduleItem scheduleItem=ticketWithScheduleVO.getSchedule();
            movieId=scheduleItem.getMovieId();
        }
        List<Activity> activities=activityServiceForBl.getActivitiesByMovie(movieId);
        List<Coupon> giftCoupons=new ArrayList<>();
        if (activities!=null) {
            for (Activity activity : activities) {
                Timestamp d = new Timestamp(System.currentTimeMillis());
                Timestamp startTime = activity.getStartTime();
                Timestamp endTime = activity.getEndTime();
                if (d.before(endTime) && d.after(startTime)) {
                    Coupon giftCoupon=activity.getCoupon();
                    couponService.issueCoupon(giftCoupon.getId(),userId);
                    //order里添加赠送的优惠劵
                    giftCoupons.add(giftCoupon);
                }
            }
        }
        order.setGiftCoupons(giftCoupons);
        //将-1(会员卡不存在)存入Order
        order.setVIPCardId(-1);
        orderServiceForBl.updateOrder(order);
        orderServiceForBl.cleanExpiredOrder();
        for (Coupon coupon:giftCoupons){
            orderServiceForBl.insertOrderGiftCoupon(order.getId(),coupon.getId());
        }
        if (order.getState()==0){
            orderServiceForBl.updateOrderState(order.getId(),1);
        }
        return "成功";
    }

    public double sub(double a,double b){
        BigDecimal a1=new BigDecimal(Double.toString(a));
        BigDecimal b1=new BigDecimal(Double.toString(b));
        return b1.subtract(a1).doubleValue();
    }


}
