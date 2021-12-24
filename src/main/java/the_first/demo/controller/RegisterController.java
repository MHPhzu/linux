package the_first.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
//import the_first.demo.config.passwordEncoder;
import the_first.demo.model.User;
import the_first.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.apache.commons.codec.digest.Md5Crypt;

@RestController
@RequestMapping("/ayUser")
public class RegisterController {
    @Resource
    UserService userService;
//    @Resource
//    the_first.demo.config.passwordEncoder passwordEncoder;
//    @RequestMapping(value = "/doregister")
//    public String doregister(String stunum, String stuphone, String stupwd, String stuemail) {
//        User user = new User();
//        user.setStupwd(stupwd);
//        user.setStunum(stunum);
//        user.setStuphone(stuphone);
//        user.setStuemail(stuemail);
//        return "alert('wdnmd')";
//    }
    @RequestMapping("/doregister")
    public String register(String stunum, String stuphone, String stupwd, String stuemail) {

        String data = "";
        User user = new User();
        user.setStunum(stunum);
        String alp = "qwertyuioplkjhgfdsazxcvbnm";
        String salt = "";
        for(int i = 0;i < 5;++i) {
            Random random = new Random();
            int nextInt = random.nextInt(25);
            salt += alp.charAt(nextInt);
        }
        String truepwd = stupwd + salt;
        String md5 = DigestUtils.md5DigestAsHex(truepwd.getBytes());
//        String pwd = Md5Crypt.md5Crypt(stupwd.getBytes(), "$1$qqqqqqqq");
//        System.out.println(md5);
//        System.out.println(pwd);
//        String encode = passwordEncoder.bCryptPasswordEncoder().encode(stupwd);
//        System.out.println(encode);
//        user.setStupwd(encode);
        user.setStupwd(md5);
        user.setStuemail(stuemail);
        user.setStuphone(stuphone);
        user.setSalt(salt);
        if("".equals(stunum) || "".equals(stuemail) || "".equals(stupwd) || "".equals(stuphone)) return "alert('字段没填')";
        data = userService.findusers(user);
        System.out.println("----"+data);
        if (data.equals("用户已存在")) {
            data = "alert('用户已存在');" +
                    "location.href = 'register.html';";
            return data;
        }
//        System.out.println("----"+data);
        data = userService.findemail(user);
        if (data.equals("邮箱已存在")) {
            data = "alert('邮箱已存在');" +
                    "location.href = 'register.html';";
            return data;
        }
//        System.out.println("----"+data);
        data = userService.findphone(user);
        if (data.equals("手机号已存在")) {
            data = "alert('手机号已存在');" +
                    "location.href = 'register.html';";
            return data;
        }
//        System.out.println("----"+data);
//        System.out.println("asd");
        int stat = userService.insertusers(user);
        System.out.println(stat);
        if(stat == 1)
        {
            data = "alert('注册成功');" +
                    "location.href = 'login';";
        }else {
            data = "alert('注册失败');" +
                    "location.href = 'register.html';";
        }
//        System.out.println(data);
        return data;
    }
}
