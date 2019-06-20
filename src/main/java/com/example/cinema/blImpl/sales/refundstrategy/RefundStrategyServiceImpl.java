package com.example.cinema.blImpl.sales.refundstrategy;

import com.example.cinema.bl.sales.RefundStrategyService;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.data.sales.RefundStrategyMapper;
import com.example.cinema.po.Movie;
import com.example.cinema.po.RefundStrategy;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.RefundStrategyForm;
import com.example.cinema.vo.RefundStrategyVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefundStrategyServiceImpl implements RefundStrategyService{
    @Autowired
    private RefundStrategyMapper refundStrategyMapper;
    @Autowired
    private MovieServiceForBl movieServiceForBl;

    @Override
    public ResponseVO getAllRefundStrategy(){
        try{
            return ResponseVO.buildSuccess(refundStrategyList2RefundStrategyVOList(refundStrategyMapper.selectAllRefundStrategy()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getRefundStrategyById(int id){
        try{
            return ResponseVO.buildSuccess(new RefundStrategyVO(refundStrategyMapper.selectRefundStrategyById(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addRefundStrategy(RefundStrategyForm refundStrategyForm){
        try{
            RefundStrategy refundStrategy=new RefundStrategy();
            refundStrategy.setNewRefundStrategy(refundStrategyForm);
            refundStrategyMapper.insertRefundStrategy(refundStrategy);
            if (refundStrategyForm.getMovieFormList()!=null&&refundStrategyForm.getMovieFormList().size()!=0) {
                refundStrategyMapper.insertRefundStrategyAndMovie(refundStrategy.getId(), refundStrategyForm.getMovieFormList());
            }
            else if (refundStrategyForm.getMovieFormList()!=null&&refundStrategyForm.getMovieFormList().size()==0){
                //删除所有策略和电影关联表
                List<RefundStrategy> tempList=refundStrategyMapper.selectAllRefundStrategy();
                for(RefundStrategy refundStrategy1: tempList){
                    refundStrategyMapper.deleteRefundStrategyAndMovie(refundStrategy1.getId());
                }
                List<Movie> movieList=movieServiceForBl.selectAllMovie();
                List<Integer> movieIdList=new ArrayList<>();
                for (Movie movie:movieList){
                    int movieId=movie.getId();
                    movieIdList.add(movieId);
                }
                refundStrategyMapper.insertRefundStrategyAndMovie(refundStrategy.getId(),movieIdList);
            }
            //Strategy和id的联系要进行处理
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO updateRefundStrategy(RefundStrategyForm refundStrategyForm){
        try{
            RefundStrategy refundStrategy=new RefundStrategy();
            refundStrategy.setNewRefundStrategy(refundStrategyForm);
            refundStrategyMapper.updateRefundStrategy(refundStrategy);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO deleteRefundStrategyById(int id){
        try{
            refundStrategyMapper.deleteRefundStrategy(id);
            refundStrategyMapper.deleteRefundStrategyAndMovie(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public RefundStrategy selectRefundStrategyByMovieId(int movieId){
        return refundStrategyMapper.selectRefundStrategyByMovieId(movieId);
    }



    private List<RefundStrategyVO> refundStrategyList2RefundStrategyVOList(List<RefundStrategy> refundStrategiesList){
        List<RefundStrategyVO> refundStrategyVOList = new ArrayList<>();
        for(RefundStrategy refundStrategy : refundStrategiesList){
            refundStrategyVOList.add(new RefundStrategyVO(refundStrategy));
        }
        return refundStrategyVOList;
    }
}
