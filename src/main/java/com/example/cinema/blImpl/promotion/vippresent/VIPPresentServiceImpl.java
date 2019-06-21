package com.example.cinema.blImpl.promotion.vippresent;

import com.example.cinema.bl.promotion.VIPPresentService;
import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.blImpl.user.AccountServiceForBl;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.CouponVO;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class VIPPresentServiceImpl implements VIPPresentService {
    @Autowired
    private VIPCardMapper vipCardMapper;
    @Autowired
    private CouponServiceForBl couponServiceForBl;
    @Autowired
    private AccountServiceForBl accountServiceForBl;
    @Autowired
    private VIPStrategyService vipStrategyService;

    private List<VIPRecordVO> getVIPCardVOList(List<VIPCard> vipCardList){
        List<VIPRecordVO> vipRecordVOList=new ArrayList<>();
        for (int i=0;i<vipCardList.size();i++){
            VIPCard tempCard=vipCardList.get(i);
            VIPRecordVO tempRecordVO=new VIPRecordVO();

            tempRecordVO.setUserId(tempCard.getUserId());
            tempRecordVO.setUserName((accountServiceForBl.getUserById(tempCard.getUserId())).getUsername());
            tempRecordVO.setVipName((vipStrategyService.selectVIPStrategyByAmount(tempCard.getTargetMoney(),tempCard.getGiftMoney())).getName());
            tempRecordVO.setTotalAmount(tempCard.getTotalAmount());
            Date date=new Date();
            date=tempCard.getJoinDate();
            tempRecordVO.setJoinDate(date);
            Date date2=new Date();
            date2=tempCard.getLastOrderDate();
            tempRecordVO.setLastOrderDate(date2);

            vipRecordVOList.add(tempRecordVO);

        }
        return vipRecordVOList;
    }

    @Override
    public ResponseVO getVIPRecordListByAI() {
        try{
            List<VIPCard> vipCardList=vipCardMapper.selectAllVIPCard();
            List<VIPRecordVO> vipRecordVOList=getVIPCardVOList(vipCardList);
            //实现AI算法
            Collections.sort(vipRecordVOList,new Comparator<VIPRecordVO>() {
                @Override
                public int compare(VIPRecordVO p1, VIPRecordVO p2) {
                    Date date=new Date();
                    /*AI排序*/
                    //优先级：一个月内：总金额>距离上次消费天数差
                    //        大于一个月：天数差>总金额
                    if(((date.getTime()-p1.getLastOrderDate().getTime())/ (1000 * 60 * 60 * 24))*p1.getTotalAmount()*0.033+p1.getTotalAmount()< ((date.getTime()-p2.getLastOrderDate().getTime())/ (1000 * 60 * 60 * 24))*p2.getTotalAmount()*0.033+p2.getTotalAmount()){
                        return 1;
                    }
                    if(p1.getTotalAmount() == p2.getTotalAmount()){
                        return 0;
                    }
                    return -1;
                }
            });

            return ResponseVO.buildSuccess(vipRecordVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPRecordListByAmount() {
        try{
            List<VIPCard> vipCardList=vipCardMapper.selectAllVIPCard();
            List<VIPRecordVO> vipRecordVOList=getVIPCardVOList(vipCardList);
            Collections.sort(vipRecordVOList,new Comparator<VIPRecordVO>() {
                @Override
                public int compare(VIPRecordVO p1, VIPRecordVO p2) {
                    /*按充值总额升序排序*/
                    if(p1.getTotalAmount() < p2.getTotalAmount()){
                        return 1;
                    }
                    if(p1.getTotalAmount() == p2.getTotalAmount()){
                        return 0;
                    }
                    return -1;
                }
            });
            return ResponseVO.buildSuccess(vipRecordVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPRecordListByDate() {
        try{
            List<VIPCard> vipCardList=vipCardMapper.selectAllVIPCard();
            List<VIPRecordVO> vipRecordVOList=getVIPCardVOList(vipCardList);
            Collections.sort(vipRecordVOList,new Comparator<VIPRecordVO>() {
                @Override
                public int compare(VIPRecordVO p1, VIPRecordVO p2) {
                    /*按日期升序排序*/
                    if(p1.getLastOrderDate().before(p2.getLastOrderDate())){
                        return 1;
                    }
                    if(p2.getLastOrderDate().before(p1.getLastOrderDate())){
                        return -1;
                    }
                    return 0;
                }
            });
            return ResponseVO.buildSuccess(vipRecordVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAllCoupons() {
        try{
            Timestamp d = new Timestamp(System.currentTimeMillis());//获取当前时间
            List<Coupon> couponList=couponServiceForBl.selectAllCoupon();
            List<CouponVO> couponVOList=new ArrayList<>();
            for(Coupon coupon:couponList){
                if(d.before(coupon.getEndTime())) {
                    couponVOList.add(coupon.toVO());
                }
            }
            return ResponseVO.buildSuccess(couponVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addCouponForVIP(List<Integer> userIdList, int couponId) {
        try{
            for(Integer i:userIdList){
                couponServiceForBl.insertCouponUser(couponId,i);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
