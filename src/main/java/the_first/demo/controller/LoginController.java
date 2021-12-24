package the_first.demo.controller;


import org.springframework.util.DigestUtils;
//import the_first.demo.config.passwordEncoder;
//import the_first.demo.config.passwordEncoder;
import the_first.demo.model.User;
import the_first.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/ayUser")
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping("/check")
    public String login(String stunum, String stupwd, HttpSession session) {
        String data = "";


        User user = new User();
        user.setStunum(stunum);
        User u = userService.findUserByNum(stunum);
        String salt = u.getSalt();
//        boolean matches = passwordEncoder.bCryptPasswordEncoder().matches(stupwd, "$2a$10$GT0TjB5YK5Vx77Y.2N7hkuYZtYAjZjMlE6NWGE2Aar/7pk/Rmhf8S");
//        System.out.println(matches);
        user.setStupwd(DigestUtils.md5DigestAsHex((stupwd + salt).getBytes()));
//        System.out.println(matches);
        data = userService.checkusers(user);
        if (data.equals("成功")) {            //登录成功
            session.setAttribute("stunum", stunum);
            data =
                    "location.href = 'main';";
        }else {
            //登录失败
            data = "alert('登录失败')";
            session.removeAttribute("stunum");
        }

        return data;
    }

    @RequestMapping(value = "/loginVerCode")
    public String loginVerCode(String verCode, HttpSession session) {
        String code = (String) session.getAttribute("code");
        if(code == null) {
            return "alert('请先获取验证码')";
        }
        if(verCode.equals(code)) {
            session.setAttribute("stunum", "codeLogin");
            return "location.href = 'main'";
        }else {
            return "alert('验证码错误')";
        }
    }
}
