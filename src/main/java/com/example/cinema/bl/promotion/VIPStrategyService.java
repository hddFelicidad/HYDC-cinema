package com.example.cinema.bl.promotion;

import com.example.cinema.po.VIPStrategy;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPStrategyForm;
import com.example.cinema.vo.VIPStrategyVO;

import java.util.List;

/**
 * @author pooh
 * @date 2019/6/10 10:00 PM
 */
public interface VIPStrategyService {
    /**
     * 发布新的VIP策略
     * @return
     */
    ResponseVO publishVIPStrategy(VIPStrategyForm vipStrategyForm);
    /**
     * 删除VIP策略
     * @return
     */
    ResponseVO takeoffVIPStrategy(VIPStrategyForm vipStrategyForm);
    /**
     * 更新VIP策略
     * @return
     */
    ResponseVO updateVIPStrategy(VIPStrategyForm vipStrategyForm);
    /**
     * 获取所有的VIP策略
     * @return
     */
    ResponseVO getVIPStrategy();
    /**
     * 查看所有上架的VIP优惠策略
     * @return
     */
    List<VIPStrategy> getUsableVIPStrategies();
    /**
     * 查看所有VIP优惠策略
     * @return
     */
    List<VIPStrategy> selectVIPStrategies();
    /**
     * 通过目标金额和赠送金额查看VIP优惠策略
     * @param targetMoney
     * @param giftMoney
     * @return
     */
    VIPStrategy selectVIPStrategyByAmount(double targetMoney,double giftMoney);
}
