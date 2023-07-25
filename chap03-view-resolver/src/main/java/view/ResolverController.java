package view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")  // /이후 다 여기서 처리
public class ResolverController {
    @GetMapping("string")
    public String stringReturing(Model model) {
        model.addAttribute("forwardMessage", "문자열로 뷰이름을 반환함...");
        /*
        * 문자열로 뷰 이름을 반환한다는 것은 반환 후
        * ThymelafViewResolver에게 resources/templaters/ prefix로 .html을 suffix로 하여
        * resources/ templates/result.html 파일을 응답 뷰로 설정하라는 의미가 된다.
        *
        * */
        return "result";
    }


    @GetMapping("string-redirect")
    public  String StringRedirect(){
        return "redirect:/";
    }

    /*
    * 기본적으로 redirect 시에 재요청이 발생되며 request scop는 소멸된다.
    * 하지만 스프링에서는 리다이렉트어트리뷰트 타입을 통해 리다잍렉트 시 속성 값을 저정할 수 있는 기능을 제공한다.
    * */

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr){

        /*
        * 리다이렉트 시 플래쉬 영억에 담아서 리다이렉트 할 수 있다.
        * 자동으로 모델에 추가되기 때문에 리퀘스트스코프에서 값을 꺼내면 된다.
        * 세션에 임시로 값을 담고 소멸하는 방식이기 때문에 세션에 동일한 키 값이 존재하지 않아야한다.
        * 여기서 키는 어트리뷰트네임이다.
        *
        * */

        rttr.addFlashAttribute("flashMessage1","리다이렉트 attr 사용하여 redirect....");
        return "redirect:/";
    }


    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturing(ModelAndView modelAndView){
        /*
        * 모델과 뷰를 합친 개념이다.
        * 핸들러 어댑터가 핸들러 메소드를 호출하고 반환받은 문자열을 모델앤뷰로 만들어 디스패처 서블릿에 반환한다.
        * 이 때 문자열을 반환해도 되지만 모델앤뷰를 미리 만들어 반환할 수 있다.
        *
        * */

        modelAndView.addObject("forwardMessage","modelAndView을 이용한 모델과 뷰 반환");
        modelAndView.setViewName("result");
        return modelAndView;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("modelanview-redirect-attr")
    public ModelAndView modelAndRedirect(ModelAndView modelAndView,RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2", "모델앤 뷰를 이용한 redirect attr");
        modelAndView.setViewName("redirect:/");
        return  modelAndView;
    }
}
