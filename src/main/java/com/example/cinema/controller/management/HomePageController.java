package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HomePageService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @Autowired
    private HomePageService homePageService;

    @RequestMapping(value = "/home/movie",method = RequestMethod.GET)
    public ResponseVO getValueMovie(){
        return homePageService.getValueMovie();
    }
}
