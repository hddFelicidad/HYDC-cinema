package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.bl.sales.RechargeService;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.Recharge;
import com.example.cinema.po.VIPStrategy;
import com.example.cinema.vo.*;
import com.example.cinema.po.VIPCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService, VIPServiceForBl {
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    VIPStrategyService vipStrategyService;
    @Autowired
    RechargeService rechargeService;

    @Override
    public ResponseVO addVIPCard(int userId,VIPInfoForm vipInfoForm) {
        Timestamp d = new Timestamp(System.currentTimeMillis());//获取当前时间
        VIPCard vipCard = new VIPCard();
        VIPCard usedVipCard=vipCardMapper.selectCardByUserId(userId);
        if (usedVipCard!=null){
            vipCard.setUserId(userId);
            vipCard.setBalance(usedVipCard.getBalance());
            vipCardMapper.deleteCardById(usedVipCard.getId());
        }
        else {
            vipCard.setUserId(userId);
            vipCard.setBalance(0);
        }
        vipCard.setTargetMoney(vipInfoForm.getTargetMoney());
        vipCard.setGiftMoney(vipInfoForm.getGiftMoney());
        vipCard.setPrice(vipInfoForm.getPrice());
        vipCard.setJoinDate(d);
        vipCard.setTotalAmount(0);
        vipCard.setLastOrderDate(d);
        try {
            vipCardMapper.insertOneCard(vipCard);
            VIPCardVO vipCardVO=vipCard.toVO();
            return ResponseVO.buildSuccess(vipCardVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfo() {
        try {
            List<VIPStrategy> vipStrategyList= vipStrategyService.selectVIPStrategies();
            List<VIPInfoVO> vipInfoVOList=new ArrayList<>();
            for (VIPStrategy vipStrategy:vipStrategyList){
                VIPInfoVO vipInfoVO=new VIPInfoVO();
                vipInfoVO.setPrice(vipStrategy.getPrice());
                vipInfoVO.setTargetMoney(vipStrategy.getTargetMoney());
                vipInfoVO.setGiftMoney(vipStrategy.getGiftMoney());
                vipInfoVOList.add(vipInfoVO);
            }
            return ResponseVO.buildSuccess(vipInfoVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {

        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        double balance = vipCard.calculate(vipCardForm.getAmount());
        vipCard.setBalance(vipCard.getBalance()+balance);
        Recharge recharge=new Recharge();
        recharge.setUserId(vipCard.getUserId());
        recharge.setBalance(vipCard.getBalance());
        recharge.setRechargeMoney(vipCardForm.getAmount());
        recharge.setGiftMoney(balance-vipCardForm.getAmount());
        rechargeService.insertRecharge(recharge);
        try {
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            VIPCardVO vipCardVO=new VIPCardVO();
             vipCardVO.setId(vipCard.getId());
             vipCardVO.setUserId(vipCard.getUserId());
             vipCardVO.setPrice(vipCard.getPrice());
             vipCardVO.setTargetMoney(vipCard.getTargetMoney());
             vipCardVO.setGiftMoney(vipCard.getGiftMoney());
             vipCardVO.setBalance(vipCard.getBalance());
             vipCardVO.setJoinDate(vipCard.getJoinDate());
            return ResponseVO.buildSuccess(vipCardVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if(vipCard==null){
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public VIPCard getVIPCardByUserId(int userId){
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            return vipCard;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseVO getValueCard(){
        try {
            List<VIPStrategy> vipStrategyList= vipStrategyService.selectVIPStrategies();
            double discount=0;
            VIPStrategy valueVipStrategy=new VIPStrategy();
            for (VIPStrategy vipStrategy:vipStrategyList){
                double price=vipStrategy.getPrice();
                double targetMoney=vipStrategy.getTargetMoney();
                double giftMoney=vipStrategy.getGiftMoney();
                double n=giftMoney/(targetMoney+price);
                if (n>=discount){
                    discount=n;
                    valueVipStrategy=vipStrategy;
                }
            }
            VIPStrategyVO valueVipStrategyVO=valueVipStrategy.toVO();
            return ResponseVO.buildSuccess(valueVipStrategy);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public VIPCard selectCardById(int id){
        return vipCardMapper.selectCardById(id);
    }

    @Override
    public void updateCardBalance(int id,double newBalance){
        vipCardMapper.updateCardBalance(id,newBalance);
    }

    @Override
    public void updateCardTotalAmount(int id,double realTotal){
        vipCardMapper.updateCardTotalAmount(id,realTotal);
    }

    @Override
    public void updateCardLastOrderDate(int id , Timestamp tempTime){
        vipCardMapper.updateCardLastOrderDate(id,tempTime);
    }
}
