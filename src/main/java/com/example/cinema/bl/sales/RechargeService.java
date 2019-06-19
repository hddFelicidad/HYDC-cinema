package com.example.cinema.bl.sales;

import com.example.cinema.po.Recharge;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

/**
 * @author bjh
 * @date 2019/6/2 5:00 PM
 */
public interface RechargeService {

    /**
     * 获取充值记录
     */
    ResponseVO getRecord(int userId);

    /**
     * 获取充值记录
     * @param userId
     * @return
     */
    List<Recharge> getRechargeRecord(int userId);

    /**
     * 新增消费记录
     * @param recharge
     */
    void insertRecharge(Recharge recharge);
}
