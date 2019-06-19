package com.example.cinema.data.promotion;

import com.example.cinema.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Mapper
public interface CouponMapper {
    /**
     * 插入一个优惠劵
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon);
    /**
     * 根据用户id获取优惠劵
     * @param userId
     * @return
     */
    List<Coupon> selectCouponByUser(int userId);
    /**
     * 根据优惠劵id获取优惠劵
     * @param id
     * @return
     */
    Coupon selectById(int id);
    /**
     * 插入一个优惠劵与用户的关联
     * @param couponId
     * @param userId
     * @return
     */
    void insertCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);
    /**
     * 删除一个优惠劵与用户的关联
     * @param couponId
     * @param userId
     * @return
     */
    void deleteCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);
    /**
     * 根据用户id和消费金额获取想要优惠劵
     * @param userId
     * @param amount
     * @return
     */
    List<Coupon> selectCouponByUserAndAmount(@Param("userId") int userId,@Param("amount") double amount);


    List<Coupon> selectAllCoupon();


}
