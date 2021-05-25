package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {
    
    // HTML <form> 태그, Ajax 검색
    // http post body -> data
    // request form ... json, xml, multipart-form / text-plain 등의 형식

    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        
        return searchParam;
    }
}
