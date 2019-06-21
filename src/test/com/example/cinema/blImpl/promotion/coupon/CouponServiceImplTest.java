package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.vo.CouponForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CouponServiceImplTest {
    @Autowired
    CouponServiceImpl couponService;


    @Test
    public void getCouponsByUser() {
    }

    @Test
    public void addCoupon() {
        CouponForm newCouponForm=new CouponForm();
        newCouponForm.setName("sdhf");
        newCouponForm.setDescription("serg");
        newCouponForm.setDiscountAmount(20.0);
        couponService.addCoupon(newCouponForm);
    }

    @Test
    public void issueCoupon() {
    }

    @Test
    public void getCouponById() {
    }

    @Test
    public void getCouponListByUserId() {
    }

    @Test
    public void deleteCouponUser() {
    }

    @Test
    public void selectAllCoupon() {
    }

    @Test
    public void insertCouponUser() {
    }
}