package com.example.cinema.blImpl.management.home;

import com.example.cinema.bl.management.HomePageService;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.po.Movie;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    HomePageService homePageService;
    @Autowired
    MovieServiceForBl movieServiceForBl;

    @Override
    public ResponseVO getValueMovie(){
        List<Movie> movies=movieServiceForBl.selectAllMovie();
        List<Movie> movieList=new ArrayList<>(8);
        int count=0;
        for (Movie movie:movies){
            movieList.add(movie);
            count++;
            if (count==8){
                break;
            }
        }
        return ResponseVO.buildSuccess(movieList);
    }

}
