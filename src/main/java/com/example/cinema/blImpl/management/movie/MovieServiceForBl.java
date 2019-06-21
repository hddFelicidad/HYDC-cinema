package com.example.cinema.blImpl.management.movie;

import com.example.cinema.po.Movie;
import com.example.cinema.vo.MovieVO;

import java.util.List;

public interface MovieServiceForBl {
    /**
     * 根据id查找电影
     * @param id
     * @return
     */
    Movie getMovieById(int id);

    /**
     * 查找所有电影
     * @return
     */
    List<Movie> selectAllMovie();
}
