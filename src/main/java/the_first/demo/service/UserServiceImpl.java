package the_first.demo.service;

import the_first.demo.dao.WluserDao;
import the_first.demo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements the_first.demo.service.UserService {

    @Resource
    WluserDao wluserDao;

    @Override
    public String checkusers(User users) {

        String data = "";
        User user = wluserDao.findwluserByStunum(users.getStunum());

        if (user == null) {
            user = wluserDao.findwluserByStuphone(users.getStunum());
            if (user == null) {
                data = "alert('用户不存在');";
            }
        }
        //此处不再使用else，否则在学号不存在但号码存在的情况下会引发data为空
        if (user != null){
            if (users.getStupwd().equals(user.getStupwd())) {
                data = "成功";
            }else {
                data = "alert('密码错误');";
            }
        }
        return data;
    }

    @Override
    public String findusers(User users) {
        String data = "";
        User user = wluserDao.findwluserByStunum(users.getStunum());
        if(user == null) {
            data = "成功";
        }
        else {
            data = "用户已存在";
        }

        return data;
    }

    @Override
    public String findphone(User users) {
        String data = "";
        User user = wluserDao.findwluserByStuphone(users.getStuphone());
        if(user == null)
        {
            data = "成功";
        }
        else{
            data = "手机号已存在";
        }
        return data;
    }

    @Override
    public String findemail(User users) {
        String data = "";
        User user = wluserDao.findwluserByStuemail(users.getStuemail());
        if(user == null)
        {
            data = "成功";
        }
        else{
            data = "邮箱已存在";
        }
        return data;
    }

    @Override
    public int insertusers(User users) {

        return wluserDao.insertuser(users);
    }

    @Override
    public int updatePwd(User user) {
        return wluserDao.updatePwd(user);
    }

    @Override
    public String findPhoneByNum(String stunum) {
        return wluserDao.findwluserByStunum(stunum).getStuphone();
    }

    @Override
    public User findUserByNum(String stunum) {
        return wluserDao.findwluserByStunum(stunum);
    }


}
