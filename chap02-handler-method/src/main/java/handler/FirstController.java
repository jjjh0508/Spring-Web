package handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Map;

@Controller // 빈에 등록됨
@RequestMapping("/first/*")
@SessionAttributes("id")
public class FirstController {

    /*핸들러 메소드에 파라미터로 특정 몇 가지 타입을 선언하게 되면 핸들러 메소드 호출시 인자로 값을 전달 해준다.*/

    /* 컨트롤러 핸들러 메서드의 반환값을  void로 설정하면 요청 주소가 view의 이름이 된다.
     * -> first/regist 요청이 들어오면 first/regist 뷰가 응답을 하게 된다.
     *
     *
     *
     * */

    @GetMapping("regist")
    public void regist() {
    }

//    @GetMapping("regist1")
//    public String regist2(){
//        return "/first/regist";
//
//    }


    @PostMapping("regist")
    public String registPost(Model model, WebRequest webRequest) {
        String name = webRequest.getParameter("name");
        int price = Integer.parseInt(webRequest.getParameter("price"));
        int categoryCode = Integer.parseInt(webRequest.getParameter("categoryCode"));

        System.out.println(price);
        String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + " 번 카테고리에" + price + " 원으로 등록";

        model.addAttribute("message", message);

        return "first/messagePrinter";

    }


    @GetMapping("modify")
    public void modifyMethod() {
    }

    /*
     * @RequestPram으로 요청 파라미터 전달하기
     * 요청 파라미터를 매팅하여 호출시 값을 넣어주는 어노테이션으로 매개 변수 앞에 작성한다.
     * form의 name 속성 값과 매개변수의 이름이 다른 경우 @RequestPram("name")을 설정 하면 된다.
     * 또한 어노테이션은 생략 가능하지만 명시적으로 작성하는 것이 의미 파악이 쉽다.
     * 작성하지 않으면 required=false를 적용
     *
     * 전달하는 form의 name 속성이 일치하는 것이 없는 경우 400 에러가 발생하는데 이는 required 속성의 기본 값이 true이고  파라미터를 반드시 줘야하며
     * 속성을 false로 하게되면 해당 값이 존재하지 null로 처리하여 에러가 발생하지 않는다.
     *
     * 여기서는 ""로 넘어와서 속성 효과를 안받는듯 하다
     * */
    @PostMapping("modify")
    public String modifyMenuPrice(Model model, @RequestParam(required = false) String modifyName, @RequestParam(defaultValue = "0") int modifyPrice) {
        String message = modifyName + " 메뉴의 가격을 " + modifyPrice + " 원으로 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @PostMapping("modifyAll")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameters) {
        String modifyMenu = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = "메뉴의 이름을 " + modifyMenu + "(으)로, 가격을 " + modifyPrice + "원 으로 변경하였습니다.";
        model.addAttribute("message", message);

        return "first/messagePrinter";

    }

    @GetMapping("search")
    public void search() {
    }

    @PostMapping("search")
    public String searchPost(@ModelAttribute("menu") menuDTO menu) {
        System.out.println(menu);
        return "first/messagePrinter";
    }


    @GetMapping("login")
    public void login() {
    }

    /*4-1 Session 이용하기
     * httpSession을 매개변수로 선언하면 핸들러 메소드 호출 시 센셔 객체를 넣어서 호출한다.

     * */
    @PostMapping("login1")
    public String sessionTest1(HttpSession session, @RequestParam String id) {
        session.setAttribute("id", id);

        return "first/loginResult";
    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {
        session.invalidate();
        return "first/loginResult";

    }


    /*
     * 4-2 @SessionAttributes를 이용해여 session에 값 담기
     * 클래스 레벨에 @SessionAttributes 어노테이션을 이용하여 세션에 값을 담을 key 값으로 설정해두면
     * model 영역에 해당하는 key로 값이 추가되는 경우 session에 자동으로 등록한다.
     *
     *
     * */

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {
        model.addAttribute("id", id);
        return "first/loginResult";
    }


    /*SessionAttribute로 등로된 값은 session의 상태를 관리하는 SessionStatus setComplate 메소드를 호출해야 사용이 만료된다.*/
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "first/loginResult";
    }


    @GetMapping("body")
    public void body(){}

    /*5.@RequestBoby를 이용하는 방법
     * 해당 어노테이션은 http 본문 자체를 읽어 부분을 모델로 변환시켜주는 어노테이션이다.
     *
     * 출력을 해보면 쿼리스트링 형태의 문자열이 전송된다.
     * json으로 전달하는 경우 jackson의 컨버터로 자동 파싱하여 사용할 수 있다.
     *
     * 주로 restapi  작성시 많이 사용되며, 일번적인 form을 전송할 때는 거의 사용하지 않는다.
     *
     * 추가 적으로 헤더에 대한 정보도
     * @RequestHeader 어노테이션을 이용해서 가져올 수 있다.
     * @CookieValue를 이용해서 쿠키 정보도 쉽게 불러옳 수 있다.
     *
     * */
    @PostMapping("body")
    public void bodyTest(@RequestBody String body,
                         @RequestHeader("content-type") String contentType,
                         @CookieValue(value = "JSESSIONID", required = false) String sessionId) {

        System.out.println(contentType);
        System.out.println(sessionId);
        System.out.println(body);
        System.out.println(URLDecoder.decode(body));
    }
}
