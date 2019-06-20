package com.example.cinema.vo;

import com.example.cinema.po.Hall;


public class HallVO {
    /**
     * 是否可修改性
     */
    private boolean isChangable;
    /**
     * 影厅id
     */
    private Integer id;
    /**
     * 影厅名称
     */
    private String name;
    /**
     * 影厅座位行数
     */
    private Integer row;
    /**
     * 影厅座位列数
     */
    private Integer column;

    public HallVO(Hall hall){
        this.id = hall.getId();
        this.name = hall.getName();
        this.row = hall.getRow();
        this.column = hall.getColumn();
    }

    public boolean getIsChangable() { return isChangable;}

    public void setIsChangable(boolean isChangable){this.isChangable=isChangable;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
