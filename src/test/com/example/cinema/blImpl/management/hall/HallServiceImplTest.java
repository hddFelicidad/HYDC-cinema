package com.example.cinema.blImpl.management.hall;

import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.HallVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class HallServiceImplTest {


    @Test
    public void searchAllHall() {
    }

    @Test
    public void selectHallById() {
    }

    @Test
    public void addHall() {
        HallServiceImpl hallServiceImpl=new HallServiceImpl();
        HallForm hallForm=new HallForm();
        hallForm.setColumn(3);
        hallForm.setRow(4);
        hallForm.setName("hdfv");
        hallServiceImpl.addHall(hallForm);
        List<HallVO> hallVOList=(List<HallVO>) hallServiceImpl.searchAllHall().getContent();
        String hallName="";
        for (HallVO hall:hallVOList)
            if (hall.getName()=="hdfv")
                hallName=hall.getName();
        assertEquals("hdfv",hallName);
    }

    @Test
    public void updateHall() {
    }

    @Test
    public void deleteHall() {
    }

    @Test
    public void getHallById() {
    }
}