package the_first.demo.dao;

import the_first.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WluserDao {

    public User findwluserByStunum(@Param("stunum") String stunum);
    public User findwluserByStuphone(@Param("stuphone") String stuphone);
    public User findwluserByStupwd(@Param("stupwd") String stupwd);
    public User findwluserByStuemail(@Param("stuemail") String stuemail);
    public Integer insertuser(@Param("user") User user);
    public int updatePwd(@Param("user") User user);


}
