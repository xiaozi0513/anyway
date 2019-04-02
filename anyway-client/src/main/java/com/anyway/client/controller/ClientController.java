package com.anyway.client.controller;

import com.anyway.client.remote.AnywayApiRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author: wang_hui
 * @date: 2018/12/29 下午3:32
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private AnywayApiRemote anywayApiRemote;

    @RequestMapping("/user/all")
    public String getUserList() {
        return anywayApiRemote.listUser();
    }

    public static void main(String[] args) {
//        List<String> list = null;
//        List<String> list1 = new LinkedList<>();
//        List<String> list2 = new LinkedList<>();
//        list2.add("aaa");
//        System.out.println(CollectionUtils.isEmpty(list));
//        System.out.println(CollectionUtils.isEmpty(list1));
//        System.out.println(CollectionUtils.isEmpty(list2));
        Map<String, Student> map = new HashMap<>();
        System.out.println(map.get("b"));
        Student student = new Student();
        student.setName("www");
        map.put("a", student);
        System.out.println(Optional.ofNullable(map).map(m->m.get("a")).map(s->s.getName()).orElse("hh"));

    }

    @RequestMapping("/user/info")
    public String getUserInfo(@RequestParam("id") Long id) {
        return anywayApiRemote.getUserById(id);
    }

    @RequestMapping("test")
    public String test() {
        log.info(">>>>>>>>>>>>>>>>>>>>");
        return "success";
    }

}

class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}