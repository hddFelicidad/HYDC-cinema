package com.example.cinema.po;

import com.example.cinema.vo.MovieBoxOfficeVO;

public class MovieBoxOffice {
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 票房(单位：元)，(PS:如果后续数据量大，可自行处理单位，如改成单位：万元)
     */
    private Integer boxOffice;
    /**
     * 电影名称
     */
    private String name;

    public MovieBoxOfficeVO toVO(){
        MovieBoxOfficeVO vo=new MovieBoxOfficeVO();
        vo.setBoxOffice(this.getBoxOffice());
        vo.setMovieId(this.getMovieId());
        vo.setName(this.getName());
        return vo;
    }
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
