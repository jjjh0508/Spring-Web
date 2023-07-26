package exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException nullPointerException) {
        System.out.println("Global 레벨에서 Exception 처리");
        return "error/nullPointer";
    }

    // 사용자 정의 예외처리를 글로벌 설정하기

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model,MemberRegistException exception) {
        model.addAttribute("exception",exception);
        System.out.println("global 레벨에서 memberException 처리");

        return "error/memberRegist";

    }

    //상위 타입인 Exception을 이용하여 구체적이지 않은 타입의 에러가 발생되었을때 처리하는 default 핸들러
    @ExceptionHandler(Exception.class)
    public String nullPointer(Exception exception) {
        return "error/default";

    }
}
