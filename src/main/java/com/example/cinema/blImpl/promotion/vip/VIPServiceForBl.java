package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.po.VIPCard;
import com.example.cinema.po.VIPStrategy;

import java.sql.Timestamp;
import java.util.List;

public interface VIPServiceForBl {
    /**
     * 根据UserId查找VIP卡
     * @param userId
     * @return
     */
    VIPCard getVIPCardByUserId(int userId);

    /**
     * 根据会员id查找VIP卡
     * @param id
     * @return
     */
    VIPCard selectCardById(int id);

    /**
     * 更新会员卡余额
     * @param id
     * @param newBalance
     */
    void updateCardBalance(int id,double newBalance);

    /**
     * 更新会员卡总消费额度
     * @param id
     * @param realTotal
     */
    void updateCardTotalAmount(int id,double realTotal);

    /**
     * 更新会员最后消费日期
     * @param id
     * @param tempTime
     */
    void updateCardLastOrderDate(int id , Timestamp tempTime);
}