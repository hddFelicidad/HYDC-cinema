package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author pooh
 * @date 2019/6/10 11:00 AM
 */
public interface CouponService {

    /**
     * 查找用户的优惠券
     * @param userId
     * @return
     */
    ResponseVO getCouponsByUser(int userId);

    /**
     * 新增优惠券
     * @param couponForm
     * @return
     */
    ResponseVO addCoupon(CouponForm couponForm);

    /**
     * 为用户新增优惠券
     * @param couponId
     * @param userId
     * @return
     */
    ResponseVO issueCoupon(int couponId,int userId);

}
