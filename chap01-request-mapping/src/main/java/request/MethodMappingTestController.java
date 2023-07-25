package request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMappingTestController {

    /*1.메소드 방식 미지정*/
    //요청 URL 설정
    //post get 다 받아줌
    @RequestMapping("/menu/regist")
    public String registMenu(Model model){
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출함");

        return "mappingResult";

    }


    /* 2. 메소드 방식 지정*/
    // 요청 url 을 value 속성에 요청 method를 mothod 속성에 설정
    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modify(Model model) {
        model.addAttribute("message", "Get 방식만 허용");

        return "mappingResult";
    }

    // 1-1,(실습) post 요청 허용 실습
    /*
    * 요청 메소드 어노테이션
    * Rest api  : swagger 명세서
    * POST      @PostMapping   : 등록할 때
    * GET       @GetMapping    : 조회할 때
    * Put       @PutMapping    : 전체에 대한 수정할 때 사용 한다.
    * Delete    @DeleteMapping : 지울 때
    * Patch     @PatchMapping  : 부분 적으로 수정을 할 떄 사용한다.
    *
    * 이 어노테이션들은 @RequestMapping 어노테이션 method 속성읋 사용하여 요청 방법을 지정하는 것과 같다.
    * 각 어노테이션은 해당 하는 요청 메소드에 대해서만 처리할 수 있도록 제한 하는 역할이다.
    *
    * */


    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model) {
        System.out.println("menu delete");
        model.addAttribute("message", "메뉴 삭제 GET 방식");

        return "mappingResult";
    }

    // 1-2 post 요청 허용 실습
}
