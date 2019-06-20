package com.example.cinema.bl.sales;

import com.example.cinema.po.RefundStrategy;
import com.example.cinema.vo.RefundStrategyForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author bjh
 * @date 2019/6/8 8:00 PM
 */
public interface RefundStrategyService {

    /**
     * 获取所有退票策略
     * @return
     */
    ResponseVO getAllRefundStrategy();

    /**
     * 通过id获取退票策略
     * @param id
     * @return
     */
    ResponseVO getRefundStrategyById(int id);

    /**
     * 新增退票策略
     * @param refundStrategyForm
     * @return
     */
    ResponseVO addRefundStrategy( RefundStrategyForm refundStrategyForm);

    /**
     * 更新退票策略信息
     * @param refundStrategyForm
     * @return
     */
    ResponseVO updateRefundStrategy(RefundStrategyForm refundStrategyForm);

    /**
     * 删除退票策略
     * @param id
     * @return
     */
    ResponseVO deleteRefundStrategyById(int id);

    /**
     * 通过电影id查找退票策略
     * @param movieId
     * @return
     */
    RefundStrategy selectRefundStrategyByMovieId(int movieId);
}
