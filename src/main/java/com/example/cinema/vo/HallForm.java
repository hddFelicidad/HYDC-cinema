package com.example.cinema.vo;

public class HallForm {
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
