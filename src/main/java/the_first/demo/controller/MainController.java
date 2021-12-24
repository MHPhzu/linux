package the_first.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    @RequestMapping("/login")
    public String test(Model model) {
        return "login";
    }
    @RequestMapping("/2048")
    public String ss(Model model) {
        return "2048";
    }
    @RequestMapping("/posts")
    public String dd(Model model) {
        return "posts";
    }

    @RequestMapping("/main")
    public String main(HttpSession session) {
        if (session.getAttribute("stunum") != null) {
            return "main";
        }else{
            return "login";
        }
    }
    @RequestMapping("/register.html")
    public String register(Model model)
    {
        return "register";
    }
    @RequestMapping("/forget_pwd.html")
    public String forget(Model model)
    {
        return "forget_pwd";
    }
    @RequestMapping("/login2.html")
    public String login2(Model model)
    {
        return "login2";
    }

}
