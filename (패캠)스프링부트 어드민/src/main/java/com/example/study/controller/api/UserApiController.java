package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.User;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController
        extends CrudController<UserApiRequest, UserApiResponse, User> {

//    private final UserApiLogicService userApiLogicService;
//
//    public UserApiController(UserApiLogicService userApiLogicService) {
//        this.userApiLogicService = userApiLogicService;
//    }
//
//    @PostConstruct
//    public void init() {
//        this.baseService = userApiLogicService;
//    }

}
