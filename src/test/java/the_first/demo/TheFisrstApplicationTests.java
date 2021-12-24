package the_first.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import the_first.demo.dao.WluserDao;
import the_first.demo.model.User;

import javax.annotation.Resource;

@SpringBootTest
class TheFisrstApplicationTests {

    @Test
    void contextLoads() {
    }




    @Resource
    private WluserDao dao;

    @Test
    public void tets() {
        User user = new User();
        user.setStunum("5");
        user.setStupwd("5");
        user.setStuemail("5");
        user.setStuphone("5");
        System.out.println(dao.insertuser(user));
    }

}
