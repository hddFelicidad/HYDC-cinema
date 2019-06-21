package com.example.cinema.data.sales;

import com.example.cinema.po.Recharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pooh
 * @date 2019/6/1 7:21 PM
 */
@Mapper
public interface RechargeMapper {

    int insertRecharge(Recharge recharge);

    List<Recharge> selectByUserId(int userId);

    Recharge selectById(int id);
}
