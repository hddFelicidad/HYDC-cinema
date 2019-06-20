package com.example.cinema.blImpl.sales.recharge;

import com.example.cinema.bl.sales.RechargeService;
import com.example.cinema.data.sales.RechargeMapper;
import com.example.cinema.po.Recharge;
import com.example.cinema.vo.RechargeVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    RechargeMapper rechargeMapper;

    @Override
    public ResponseVO getRecord(int userId){
        try {
            List<RechargeVO> rechargeVOList=new ArrayList<>();
            List<Recharge> rechargeList=rechargeMapper.selectByUserId(userId);

            for (Recharge tempRecharge:rechargeList){
                RechargeVO rechargeVO=tempRecharge.getVO();
                rechargeVOList.add(rechargeVO);
            }
            return ResponseVO.buildSuccess(rechargeVOList);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public List<Recharge> getRechargeRecord(int userId){
        try {
            List<Recharge> rechargeList=rechargeMapper.selectByUserId(userId);
            return rechargeList;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insertRecharge(Recharge recharge){
        rechargeMapper.insertRecharge(recharge);
    }
}
