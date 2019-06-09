package com.example.cinema.po;

import com.example.cinema.vo.StaffVO;

import java.sql.Date;

public class Staff {
    /**
     * id
     */
    private int id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 民族
     */
    private String nation;
    /**
     * 身份证号
     */
    private String idnumber;
    /**
     * 出生日期
     */
    private Date birth;
    /**
     * 年龄
     */
    private int age;
    /**
     * 入职时间
     */
    private Date hireDate;
    /**
     * 职位
     */
    private String position;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 员工账户用户名
     */
    private String userName;
    /**
     * 员工账户密码
     */
    private String password;

    public StaffVO getVO(){
        StaffVO vo=new StaffVO();
        vo.setId(this.id);
        vo.setName(this.name);
        vo.setBirth(this.birth);
        vo.setHireDate(this.hireDate);
        vo.setPassword(this.password);
        vo.setPosition(this.position);
        vo.setUserName(this.userName);
        vo.setGender(this.gender);
        vo.setNation(this.nation);
        vo.setIdnumber(this.idnumber);
        vo.setAge(this.age);
        vo.setPhone(this.phone);
        vo.setAddress(this.address);
        return vo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
