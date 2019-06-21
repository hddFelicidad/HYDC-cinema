package com.example.cinema.blImpl.promotion.vipstrategy;

import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.data.promotion.VIPStrategyMapper;
import com.example.cinema.po.VIPStrategy;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPStrategyForm;
import com.example.cinema.vo.VIPStrategyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VIPStrategyServiceImpl implements VIPStrategyService {
    private static final String TARGET_ERROR_MESSAGE="目标金额需大于0";
    private static final String GIFT_ERROR_MESSAGE="赠送金额需大于0";
    private static final String PRICE_ERROR_MESSAGE="会员卡价格需大于0";
    @Autowired
    VIPStrategyMapper vipStrategyMapper;

    @Override
    public ResponseVO publishVIPStrategy(VIPStrategyForm vipStrategyForm){
        try {
            VIPStrategy vipStrategy=new VIPStrategy();
            vipStrategy.setName(vipStrategyForm.getName());
            vipStrategy.setGiftMoney(vipStrategyForm.getGiftMoney());
            vipStrategy.setPrice(vipStrategyForm.getPrice());
            vipStrategy.setTargetMoney(vipStrategyForm.getTargetMoney());
            vipStrategy.setState(vipStrategyForm.getState());
            if (checkVIPStrategyInfo(vipStrategyForm)!=null){
                return ResponseVO.buildFailure(checkVIPStrategyInfo(vipStrategyForm));
            }
            vipStrategyMapper.insertOneVIPStrategy(vipStrategy);
            return ResponseVO.buildSuccess(vipStrategyMapper.selectVIPStrategyById(vipStrategy.getId()));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO takeoffVIPStrategy(VIPStrategyForm vipStrategyForm){
        try {
            vipStrategyMapper.takeoffVIPStrategy(vipStrategyForm);
            List<VIPStrategy> vipStrategyList=vipStrategyMapper.selectVIPStrategies();
            List<VIPStrategyVO> vipStrategyVOList=VIPStrategyList2VIPStrategyVOList(vipStrategyList);
            return ResponseVO.buildSuccess(vipStrategyVOList);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateVIPStrategy(VIPStrategyForm vipStrategyForm){
        try {
            if (checkVIPStrategyInfo(vipStrategyForm)!=null){
                return ResponseVO.buildFailure(checkVIPStrategyInfo(vipStrategyForm));
            }
            vipStrategyMapper.updateVIPStrategy(vipStrategyForm);
            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPStrategy(){
        try {
            List<VIPStrategy> vipStrategyList=vipStrategyMapper.selectVIPStrategies();
            List<VIPStrategyVO> vipStrategyVOList=VIPStrategyList2VIPStrategyVOList(vipStrategyList);
            return ResponseVO.buildSuccess(vipStrategyVOList);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public List<VIPStrategy> getUsableVIPStrategies(){
        List<VIPStrategy> vipStrategyList=vipStrategyMapper.selectUsableVIPStrategies();
        return vipStrategyList;

    }

    @Override
    public List<VIPStrategy> selectVIPStrategies(){
        return vipStrategyMapper.selectVIPStrategies();
    }

    @Override
    public VIPStrategy selectVIPStrategyByAmount(double targetMoney,double giftMoney){
        return vipStrategyMapper.selectVIPStrategyByAmount(targetMoney,giftMoney);
    }
    private String checkVIPStrategyInfo(VIPStrategyForm vipStrategyForm){
        if (vipStrategyForm.getPrice()<0){
            return PRICE_ERROR_MESSAGE;
        }
        else if (vipStrategyForm.getTargetMoney()<0){
            return TARGET_ERROR_MESSAGE;
        }
        else if (vipStrategyForm.getGiftMoney()<0){
            return GIFT_ERROR_MESSAGE;
        }
        return null;
    }

    private List<VIPStrategyVO> VIPStrategyList2VIPStrategyVOList(List<VIPStrategy> vipStrategyList){
        List<VIPStrategyVO> vipStrategyVOList=new ArrayList<>();
        for (VIPStrategy vipStrategy:vipStrategyList){
            VIPStrategyVO vipStrategyVO=vipStrategy.toVO();
            vipStrategyVOList.add(vipStrategyVO);
        }
        return vipStrategyVOList;
    }
}
