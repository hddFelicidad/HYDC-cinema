package com.example.cinema.data.sales;

import com.example.cinema.po.Movie;
import com.example.cinema.po.RefundStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author pooh
 * @date 2019/6/1 7:21 PM
 */
@Mapper
public interface RefundStrategyMapper {
    /**
     * 查询所有退票策略
     * @return
     */
    List<RefundStrategy> selectAllRefundStrategy();

    /**
     * 根据id查询退票策略
     * @param id
     * @return
     */
    RefundStrategy selectRefundStrategyById(int id);

    /**
     * 增加新的退票策略
     * @param refundStrategy
     * @return
     */
    int insertRefundStrategy(RefundStrategy refundStrategy);

    int insertRefundStrategyAndMovie(@Param("refundStrategyId") int refundStrategyId, @Param("movieId") List<Integer> movieId);

    /**
     * 更新退票策略
     * @param refundStrategy
     * @return
     */
    int updateRefundStrategy(RefundStrategy refundStrategy);

    //不对退票策略的电影列表进行修改
    //int updateRefundStrategyAndMovie(@Param("refundStrategyId") int refundStrategyId, @Param("movieId") List<Integer> movieId);

    /**
     * 通过movieId获取退票策略
     * @param movieId
     * @return
     */
    RefundStrategy selectRefundStrategyByMovieId(int movieId);

    /**
     * 通过id删除退票策略
     * @param id
     * @return
     */
    int deleteRefundStrategy(int id);

    /**
     * 删除关联表
     * @param id
     * @return
     */
    int deleteRefundStrategyAndMovie(int id);
}
