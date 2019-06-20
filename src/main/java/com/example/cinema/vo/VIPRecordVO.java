package com.example.cinema.vo;

import java.util.Date;

public class VIPRecordVO {

    /**
     * 用户id
     */
    private int userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * vip卡名称
     */
    private String vipName;

    /**
     * 总消费金额
     */
    private double totalAmount;

    /**
     * 办卡日期
     */
    private Date joinDate;

    /**
     * 最后一次消费日期
     */
    private Date lastOrderDate;

    public VIPRecordVO(){

    }

    public void setUserId(int userId){this.userId=userId;}
    public int getUserId(){return userId;}

    public void setUserName(String userName){this.userName=userName;}
    public String getUserName(){return userName;}

    public void setVipName(String vipName){this.vipName = vipName;}
    public String getVipName(){return vipName;}

    public void setTotalAmount(double totalAmount){this.totalAmount=totalAmount;}
    public double getTotalAmount(){return totalAmount;}

    public void setJoinDate(Date joinDate){this.joinDate=joinDate;}
    public Date getJoinDate(){return joinDate;}

    public void setLastOrderDate(Date lastOrderDate){this.lastOrderDate=lastOrderDate;}
    public Date getLastOrderDate(){return lastOrderDate;}

}
