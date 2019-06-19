package com.example.cinema.bl.promotion;

import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoForm;


/**
 * @author bjh
 * @date 2019/6/9 12:00 AM
 */

public interface VIPService {

    /**
     * 新增会员卡
     * @param userId
     * @param vipInfoForm
     * @return
     */
    ResponseVO addVIPCard(int userId, VIPInfoForm vipInfoForm);

    /**
     * 通过id获取会员卡
     * @param id
     * @return
     */
    ResponseVO getCardById(int id);

    /**
     * 获取vip信息
     * @return
     */
    ResponseVO getVIPInfo();

    /**
     * 余额换算
     * @param vipCardForm
     * @return
     */
    ResponseVO charge(VIPCardForm vipCardForm);

    /**
     * 通过用户id获取会员卡
     * @param userId
     * @return
     */
    ResponseVO getCardByUserId(int userId);

    /**
     * 获取推荐的会员卡策略
     * @return
     */
    ResponseVO getValueCard();

}
