package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.CouponVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService, CouponServiceForBl {

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    TicketMapper ticketMapper;




    @Override
    public ResponseVO getCouponsByUser(int userId) {
        try {
            List<Coupon> coupons=couponMapper.selectCouponByUser(userId);
            List<CouponVO> couponVOList=new ArrayList<>();
            for (Coupon coupon:coupons){
                CouponVO couponVO=coupon.toVO();
                couponVOList.add(couponVO);
            }
            return ResponseVO.buildSuccess(couponVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addCoupon(CouponForm couponForm) {
        try {
            Coupon coupon=new Coupon();
            coupon.setName(couponForm.getName());
            coupon.setDescription(couponForm.getDescription());
            coupon.setTargetAmount(couponForm.getTargetAmount());
            coupon.setDiscountAmount(couponForm.getDiscountAmount());
            coupon.setStartTime(couponForm.getStartTime());
            coupon.setEndTime(couponForm.getEndTime());
            couponMapper.insertCoupon(coupon);
            return ResponseVO.buildSuccess(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO issueCoupon(int couponId, int userId) {
        try {
            couponMapper.insertCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public Coupon getCouponById(int id){
        try {
            return (couponMapper.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Coupon> getCouponListByUserId(int UserId){
        try{
            return(couponMapper.selectCouponByUser(UserId));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseVO deleteCouponUser(int couponId,int userId){
        try {
            couponMapper.deleteCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public List<Coupon> selectAllCoupon(){
        return couponMapper.selectAllCoupon();
    }

    @Override
    public void insertCouponUser(int couponId,int i){
        couponMapper.insertCouponUser(couponId,i);
    }
}
