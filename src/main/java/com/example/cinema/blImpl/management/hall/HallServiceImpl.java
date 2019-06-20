package com.example.cinema.blImpl.management.hall;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService,HallServiceForBl {
    @Autowired
    private HallMapper hallMapper;
    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;

    @Override
    public ResponseVO searchAllHall() {
        try {
            return ResponseVO.buildSuccess(hallList2HallVOList(hallMapper.selectAllHall()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO selectHallById(int id){
        try {
            HallVO hallVO=new HallVO(hallMapper.selectHallById(id));
            hallVO.setIsChangable(changeableJudge(id));
            return ResponseVO.buildSuccess(hallVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addHall(HallForm hallForm){
        try {
            hallMapper.insertOneHall(hallForm);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateHall(HallForm hallForm){
        try {
            boolean judge=changeableJudge(hallForm.getId());
            if(judge) {
                hallMapper.updateHall(hallForm);
                return ResponseVO.buildSuccess();
            }
            else
                return ResponseVO.buildFailure("后续有拍片,不可修改");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deleteHall(int id){
        try {
            boolean judge=changeableJudge(id);
            if(judge) {
                hallMapper.deleteHall(id);
                return ResponseVO.buildSuccess();
            }
            else
                return ResponseVO.buildFailure("后续有拍片,不可删除");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public Hall getHallById(int id) {
        try {
            return hallMapper.selectHallById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private List<HallVO> hallList2HallVOList(List<Hall> hallList){
        List<HallVO> hallVOList = new ArrayList<>();
        for(Hall hall : hallList){
            hallVOList.add(new HallVO(hall));
        }
        return hallVOList;
    }

    //判断影厅是否可修改
    private boolean changeableJudge(int hallId){
        boolean result;
        Date startDate=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR , 10);
        Date endDate=calendar.getTime();
        List<ScheduleItem> list=scheduleServiceForBl.selectSchedule(hallId,startDate,endDate);
        if(list != null && !list.isEmpty()){
            result=false;
        }
        else
            result=true;
        return result;
    }
}
