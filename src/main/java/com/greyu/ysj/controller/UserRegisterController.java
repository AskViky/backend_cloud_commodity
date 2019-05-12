package com.greyu.ysj.controller;

import com.greyu.ysj.config.ResultStatus;
import com.greyu.ysj.entity.User;
import com.greyu.ysj.model.ResultModel;
import com.greyu.ysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1/register")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResultModel> register(String name,
                                                String password,
                                                String email,
                                                String phone) {
        System.out.println("name = "+name+", password = "+password+", email = "+email+", phone = "+phone);
        if (null == name || null == password || null == email || phone == null) {
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.DATA_NOT_NULL), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setNickName(name);
        user.setUserName(email);
        user.setPassWord(password);
        user.setPhone(Long.parseLong(phone));

        ResultModel model = this.userService.addUser(user);
        if (model.getCode() == ResultStatus.SUCCESS.getCode()) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.METHOD_FAILURE);
        }

    }

}
