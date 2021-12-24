package the_first.demo.service;

import org.springframework.stereotype.Service;
import the_first.demo.model.User;

@Service
public interface  UserService {

    //提供逻辑判断方法，处理业务逻辑
    public String checkusers (User users);
    public String findusers(User users);
    public String findphone(User users);
    public String findemail(User users);
    public int insertusers(User users);
    public int updatePwd(User user);
    public String findPhoneByNum(String stunum);
    public User findUserByNum(String stunum);
}
