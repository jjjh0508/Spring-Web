package request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.GET)
    public String modifyDeleteString(Model model) {
        model.addAttribute("message","실습 과제");
        return "test";

    }
}
