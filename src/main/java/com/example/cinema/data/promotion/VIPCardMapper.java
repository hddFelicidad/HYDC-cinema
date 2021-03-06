package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * @author pooh
 * @date 2019/6/1 7:21 PM
 */
@Mapper
public interface VIPCardMapper {

    int insertOneCard(VIPCard vipCard);

    VIPCard selectCardById(int id);

    void updateCardBalance(@Param("id") int id,@Param("balance") double balance);

    VIPCard selectCardByUserId(int userId);

    int deleteCardById(int id);

    List<VIPCard> selectAllVIPCard();



    void updateCardTotalAmount(@Param("id") int id,@Param("totalAmount") double totalAmount);

    void updateCardLastOrderDate(@Param("id") int id,@Param("lastOrderDate") Timestamp lastOrderDate);


}
