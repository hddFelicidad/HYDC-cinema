package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPStrategy;
import com.example.cinema.vo.VIPStrategyForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author pooh
 * @date 2019/6/5 7:21 PM
 */
@Mapper
public interface VIPStrategyMapper {
    /**
     * 插入一个会员策略
     * @param vipStrategy
     * @return
     */
    int insertOneVIPStrategy(VIPStrategy vipStrategy);
    /**
     * 更新会员卡策略状态
     * @param vipStrategyForm
     * @return
     */
    int takeoffVIPStrategy(VIPStrategyForm vipStrategyForm);
    /**
     * 更新会员卡策略
     * @param vipStrategyForm
     * @return
     */
    int updateVIPStrategy(VIPStrategyForm vipStrategyForm);
    /**
     * 获取所有会员策略
     * @return
     */
    List<VIPStrategy> selectVIPStrategies();
    /**
     * 获取所有上架的会员策略
     * @return
     */
    List<VIPStrategy> selectUsableVIPStrategies();
    /**
     * 根据id获取策略
     * @param id
     * @return
     */
    VIPStrategy selectVIPStrategyById(int id);
    /**
     * 根据金额获取会员策略
     * @param targetMoney
     * @param giftMoney
     * @return
     */
    VIPStrategy selectVIPStrategyByAmount(double targetMoney, double giftMoney);
}
