package com.example.cinema.bl.promotion;

import com.example.cinema.vo.ResponseVO;

import java.util.List;

/**
 * @author pooh
 * @date 2019/6/9 12:00 AM
 */
public interface VIPPresentService {

    /**
     * （智能排序）获取VIP消费记录
     * @return
     */
    ResponseVO getVIPRecordListByAI();

    /**
     * （金额排序）获取VIP消费记录
     * @return
     */
    ResponseVO getVIPRecordListByAmount();

    /**
     * （最后消费日期排序）获取VIP消费记录
     * @return
     */
    ResponseVO getVIPRecordListByDate();

    /**
     * 所有可用优惠券
     * @return
     */
    ResponseVO getAllCoupons();

    /**
     * 给VIP赠送优惠券
     * @param userIdList
     * @param couponId
     * @return
     */
    ResponseVO addCouponForVIP(List<Integer> userIdList, int couponId);


}
