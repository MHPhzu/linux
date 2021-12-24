package the_first.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import the_first.demo.model.Post;

import java.util.List;

@Mapper
public interface PostDao {
	
	void save(Post post);
	
	void delete(Long postId);
	
	void update(Post post);
	
	Post find(Long postId);
	
	List<Post> findAll();
	
}