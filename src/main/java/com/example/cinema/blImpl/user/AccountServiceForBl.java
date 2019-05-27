package com.example.cinema.blImpl.user;

import com.example.cinema.po.User;

public interface AccountServiceForBl {
    /**
     * 创建新用户
     * @param name
     * @param password
     * @param position
     */
    void createNewAccount(String name,String password,String position);

    /**
     * 通过用户id获取用户
     * @param id
     * @return
     */
    User getUserById(int id);
}
