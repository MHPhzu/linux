package the_first.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JumpController {

    /**
     * 跳转index页面
     *
     * @return
     */
    @RequestMapping("/")
    public String jump() {
        return "login";
    }

    @RequestMapping(value = "/regist")
    public String reg() {
        return "regist";
    }


    @RequestMapping(value = "/face")
    public String face() {
        return "face";
    }
}
