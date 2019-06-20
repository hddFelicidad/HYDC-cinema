package com.example.cinema.bl.user;

import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;

/**
 * @author pooh
 * @date 2019/6/3 9:00 PM
 */
public interface AccountService {

    /**
     * 注册账号
     * @return
     */
     ResponseVO registerAccount(UserForm userForm);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     * @return
     */
     UserVO login(UserForm userForm);

}
