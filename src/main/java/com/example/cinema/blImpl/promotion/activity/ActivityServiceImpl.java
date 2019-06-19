package com.example.cinema.blImpl.promotion.activity;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.po.Activity;
import com.example.cinema.po.Coupon;
import com.example.cinema.po.Movie;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by liying on 2019/4/20.
 */
@Service
public class ActivityServiceImpl implements ActivityService, ActivityServiceForBl {
    private static final String DATE_LESS_THAN_LENGTH_ERROR_MESSAGE = "结束时间不能早于开始时间";
    private static final String TARGET_ERROR_MESSAGE="需满金额应大于0";
    private static final String DISCOUNT_ERROR_MESSAGE="优惠金额应大于0";

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CouponService couponService;
    @Autowired
    MovieServiceForBl movieServiceForBl;

    @Override
    @Transactional
    public ResponseVO publishActivity(ActivityForm activityForm) {
        try {
            ResponseVO vo = couponService.addCoupon(activityForm.getCouponForm());
            Coupon coupon = (Coupon) vo.getContent();
            Activity activity = new Activity();
            activity.setName(activityForm.getName());
            activity.setDescription(activityForm.getName());
            activity.setStartTime(activityForm.getStartTime());
            activity.setEndTime(activityForm.getEndTime());
            activity.setCoupon(coupon);
            if (activity.getStartTime().after(activity.getEndTime())){
                return ResponseVO.buildFailure(DATE_LESS_THAN_LENGTH_ERROR_MESSAGE);
            }
            else if (coupon.getTargetAmount()<0){
                return ResponseVO.buildFailure(TARGET_ERROR_MESSAGE);
            }
            else if (coupon.getDiscountAmount()<0){
                return ResponseVO.buildFailure(DISCOUNT_ERROR_MESSAGE);
            }
            activityMapper.insertActivity(activity);
            if(activityForm.getMovieList()!=null&&activityForm.getMovieList().size()!=0){
                activityMapper.insertActivityAndMovie(activity.getId(), activityForm.getMovieList());
            }
            else if (activityForm.getMovieList()!=null&&activityForm.getMovieList().size()==0){
                List<Movie> movieList=movieServiceForBl.selectAllMovie();
                List<Integer> movieIdList=new ArrayList<>();
                for (Movie movie:movieList){
                    int movieId=movie.getId();
                    movieIdList.add(movieId);
                }
                activityMapper.insertActivityAndMovie(activity.getId(),movieIdList);
            }
            return ResponseVO.buildSuccess(activityMapper.selectById(activity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getActivities() {
        try {
            return ResponseVO.buildSuccess(activityMapper.selectActivities().stream().map(Activity::getVO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public List<Activity> getActivitiesByMovie(int movieId){
        try {
            return activityMapper.selectActivitiesByMovie(movieId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
