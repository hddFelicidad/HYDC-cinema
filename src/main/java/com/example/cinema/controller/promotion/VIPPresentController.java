package com.example.cinema.controller.promotion;


import com.example.cinema.bl.promotion.VIPPresentService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dxy on 2019/6/4.
 */
@RestController()
@RequestMapping("/present")
public class VIPPresentController {
    @Autowired
    private VIPPresentService vipPresentService;

    @RequestMapping(value="/rank/AI",method = RequestMethod.GET)
    public ResponseVO getVIPRecordListByAI(){return vipPresentService.getVIPRecordListByAI();}

    @RequestMapping(value="/rank/amount",method = RequestMethod.GET)
    public ResponseVO getVIPRecordListByAmount(){return vipPresentService.getVIPRecordListByAmount();}

    @RequestMapping(value="/rank/date",method = RequestMethod.GET)
    public ResponseVO getVIPRecordListByDate(){return vipPresentService.getVIPRecordListByDate();}

    @RequestMapping(value="/getCoupon",method = RequestMethod.GET)
    public ResponseVO getAllCoupons(){return vipPresentService.getAllCoupons();}

    @RequestMapping(value="/complete",method = RequestMethod.POST)
    public ResponseVO addCouponForVIP(@RequestBody List<Integer> userIdList,@RequestParam int couponId){return vipPresentService.addCouponForVIP(userIdList,couponId);}
}
