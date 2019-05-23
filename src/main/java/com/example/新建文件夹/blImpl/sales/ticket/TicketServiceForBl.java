package com.example.cinema.blImpl.sales.ticket;

import com.example.cinema.po.Ticket;

import java.util.List;

public interface TicketServiceForBl {

    /**
     * 更新电影票状态
     * @param id
     * @param state
     */
    void updateTicketState(int id,int state);

    /**
     * 通过排片查找电影票
     * @param scheduleId
     * @return
     */
    List<Ticket> selectTicketsBySchedule(int scheduleId);
}
