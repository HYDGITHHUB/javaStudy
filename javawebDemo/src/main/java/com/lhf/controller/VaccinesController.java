package com.lhf.controller;


import com.lhf.entity.Vaccines;
import com.lhf.service.VaccinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhf
 * @since 2020-09-10
 */
@Controller
@RequestMapping("//vaccines")
public class VaccinesController {

    @Autowired
    private VaccinesService vaccinesService;

    @PostMapping("/save")
    public String save(Vaccines vaccines) {
        boolean save = vaccinesService.save(vaccines);
        if (!save) {
            return "false";
        }
        return "true";
    }

    @ResponseBody
    @GetMapping("/findAll")
    public Vaccines findAll() {
        return vaccinesService.getById(1);
    }

}

