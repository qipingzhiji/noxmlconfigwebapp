package com.swagger2.controller;

import com.swagger2.dao.teacher.TeacherMapper;
import com.swagger2.entity.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhang_htao on 2019/9/22.
 */
@RestController
public class HelloController {

    @Autowired
    private TeacherMapper teacherMapper;

    @GetMapping("hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("testMybatis")
    public Object testMybatis(){
        Teacher teacher = teacherMapper.selectByPrimaryKey(1);
        return teacher;
    }
}
