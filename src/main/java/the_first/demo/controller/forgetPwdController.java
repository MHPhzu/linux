package the_first.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the_first.demo.model.User;
import the_first.demo.service.UserService;
import the_first.demo.util.sendSms;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class forgetPwdController {
    @Resource
    private sendSms sendSms;
    @Resource
    UserService userService;
    @RequestMapping(value = "/sendCode")
    public String sendCode(String stuphone, HttpSession session) {
        String code = sendSms.send(stuphone);
        session.setAttribute("code", code);
        return "";
    }
    @RequestMapping(value = "/resetPwd")
    public String resetPwd(String stunum, String stupwd, String stucode, String stuphone, HttpSession session) {
        String code = (String) session.getAttribute("code");
        if(code == null) {
            return "alert('请获取验证码')";
        }
        if(stucode == null) {
            return "alert('请填写验证码')";
        }
        User user = new User();
        user.setStunum(stunum);
        User userByNum = userService.findUserByNum(stunum);
        System.out.println(userByNum);
        user.setStupwd(DigestUtils.md5DigestAsHex((stupwd + userByNum.getSalt()).getBytes()));
        String phoneByNum = userService.findPhoneByNum(stunum);
        if(!phoneByNum.equals(stuphone)) {
            return "alert('验证码错误！')";
        }
        if(stucode.equals(code)) {
            userService.updatePwd(user);
            String ret = "alert('修改密码成功！');" +
                    "location.href = 'login'";
            return ret;
        }else {
            String ret = "alert('修改密码失败！请检查验证码')";
            return ret;
        }
    }
}
