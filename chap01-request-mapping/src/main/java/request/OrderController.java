package request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/* 클래스 레벨에 @RequestMapping 어노테이션 사용
 * 클래스 레벨에 url을 공통 부분을 이용해 설정하고 나면 매번 핸들러 메소드에 url의 중복되는 내용을 작성하지 않아도 된다.
 * 이 때 와일드카드를 사용하면 조금 더 포과젉인 URL 패턴을 설정할 수 있다.
 * */

@Controller
@RequestMapping("/order/*")
public class OrderController {

    //1. Class 레벨 매핑

    @GetMapping("/regist")
    public String registOrder(Model model) {
        model.addAttribute("message", "Get 방식 주문 등록 핸들러 메소드 호출");

        return "mappingResult";

    }

    @PostMapping("/regist")
    public String registsOrder(Model model) {
        model.addAttribute("message", "POST 방식 주문 등록 핸들러 메소드 호출");

        return "mappingResult";
    }

    /*실습 아래의 핸들러 메소드가 응답할 수 있도록 html을 작성하라*/
//    @GetMapping("/modify")
//    public String modifyOrder(Model model) {
//        model.addAttribute("message", "Get 방식 주문 수정");
//
//        return "mappingResult";
//    }

    // 2. 여러개의 패턴 매핑
    // value 속성에 중괄호를 이용해서 매핑할 url을 나열한다.
    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    public String modifyString(Model model) {
        model.addAttribute("message", "post 방식의 주문 정보 수정과 주문 정보 삭제를 공통으로 처리하는 핸들러 메소드 호출함");

        return "mappingResult";
    }

    // 실습하기
//    get 방식으로 modify delete 요청을 처리하는 핸들러 메소드 작성하기.

    // path variable
    /* @PathVariable 어노테이션을 이용해서 path로 부터 변수를 받아올 수 있다.
     * path variable로 전달되는 {변수명} 값은 반드시 매개변수병과 동일 해야한다.
     * 만약 동일하지 않다면 @PathVariable("이름")을 설정해줘야한다.
     * 이는 Rest 형 웹 서비스 설계할때 유용하게 사용된다.
     */

    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo) {
        System.out.println(orderNo);
        model.addAttribute("message", orderNo + "번 주문 상세내용 조회용 핸들러 호풀함");
        return "mappingResult";
    }


    /* 4. 그 외의 다른 요청
     * @RquestMapping 어노테이션에 아무런 URL을 설정하지 않으면 요청처리에 대한 핸들러 메소드가 준비되지 않을을때 해당 메소드를 호출한다.
     *
     */

    @RequestMapping
    public String otherRequser(Model model) {
        model.addAttribute("message", "요청을 받았지만 받지 않았습니다");
        return "mappingResult";
    }
}
