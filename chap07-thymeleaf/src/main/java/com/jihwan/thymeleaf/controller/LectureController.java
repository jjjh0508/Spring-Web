package com.jihwan.thymeleaf.controller;

import com.jihwan.thymeleaf.model.MemberDTO;
import com.jihwan.thymeleaf.model.SelectCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("lecture")
public class LectureController {


    @GetMapping("expression")
    public ModelAndView expression(ModelAndView modelAndView) {
        System.out.println("확인");
        modelAndView.addObject("member", new MemberDTO("홍길동", 20, '남', "서울시 서초구"));

        modelAndView.addObject("hello", "hello!<h3>Thymealf</h3>");
        modelAndView.setViewName("/lecture/expression");
        return modelAndView;
    }


    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView modelAndView) {
        modelAndView.addObject("num", 1);
        modelAndView.addObject("str", "바나나");

        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberDTOList.add(new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberDTOList.add(new MemberDTO("김길동", 27, '여', "김포 어쩌고"));
        memberDTOList.add(new MemberDTO("단길동", 25, '여', "고양 어쩌고"));
        memberDTOList.add(new MemberDTO("고길동", 25, '여', "김포 천안역"));

        modelAndView.addObject("memberList", memberDTOList);
        modelAndView.setViewName("/lecture/conditional");
        return modelAndView;
    }


    @GetMapping("/etc")
    public ModelAndView etc(ModelAndView modelAndView) {
        SelectCriteria selectCriteria = new SelectCriteria(1, 10, 3);
        modelAndView.addObject(selectCriteria);

        MemberDTO member = new MemberDTO("김길동", 27, '여', "김포 어쩌고");
        modelAndView.addObject("member", member);
        Map<String, MemberDTO> memberMap = new HashMap<>();

        memberMap.put("m01", new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberMap.put("m02", new MemberDTO("김길동", 27, '여', "김포 어쩌고"));
        memberMap.put("m03", new MemberDTO("단길동", 25, '여', "고양 어쩌고"));
        memberMap.put("m04", new MemberDTO("고길동", 25, '여', "김포 천안역"));

        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberDTOList.add(new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberDTOList.add(new MemberDTO("김길동", 27, '여', "김포 어쩌고"));
        memberDTOList.add(new MemberDTO("단길동", 25, '여', "고양 어쩌고"));
        memberDTOList.add(new MemberDTO("고길동", 25, '여', "김포 천안역"));
        modelAndView.addObject("memberDTOList",memberDTOList);
        modelAndView.addObject("memberMap", memberMap);
        modelAndView.setViewName("/lecture/etc");
        return modelAndView;
    }
}

