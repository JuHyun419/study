package com.juhyun.springaop.aop.controller;

import com.juhyun.springaop.aop.annotation.Timer;
import com.juhyun.springaop.aop.dto.UserDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.println("--------------- @GetMapping ---------------");
//        System.out.println("get method");
//        System.out.println("id: " + id);
//        System.out.println("name: " + name);
        return id + " " + name;
    }

    @PostMapping
    public UserDto post(@RequestBody UserDto userDto) {
        System.out.println("--------------- @PostMapping ---------------");
//        System.out.println("post method");
//        System.out.println("user: " + user);
        return userDto;
    }

    @Timer
    @DeleteMapping("/delete")
    public String delete() throws InterruptedException {
        // db logic
        /* 아래 부가기능을 AOP로 처리 */
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        // 타겟 오브젝트의 메서드 실행(controller 패키지 하위 클래스들의 메소드)
//        Object result = joinPoint.proceed();
//        System.out.println("result: " + result);
//
//        stopWatch.stop();
//
//        System.out.println("Total Time: " + stopWatch.getTotalTimeSeconds());


        Thread.sleep(1000 * 2);
        return "delete";
    }

}
