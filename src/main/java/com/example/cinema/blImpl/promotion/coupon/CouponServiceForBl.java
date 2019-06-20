package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.po.Coupon;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

public interface CouponServiceForBl {
    /**
     * 根据id查找优惠劵
     * @param id
     * @return
     */
    Coupon getCouponById(int id);
    /**
     * 根据UserId查找优惠劵
     * @param UserId
     * @return
     */
    List<Coupon> getCouponListByUserId(int UserId);
    /**
     * 根据id在优惠劵里删除用户
     * @param couponId
     * @param userId
     * @return
     */
    ResponseVO deleteCouponUser(int couponId, int userId);

    /**
     * 选择所有可用优惠券
     * @return
     */
    List<Coupon> selectAllCoupon();

    /**
     * 为用户赠送优惠券
     * @param couponId
     * @param i
     */
    void insertCouponUser(int couponId,int i);
}
