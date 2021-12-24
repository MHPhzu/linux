package the_first.demo.service;


import org.springframework.stereotype.Service;
import the_first.demo.model.Post;

import java.util.Map;

@Service
public interface PostService {

	void createPost(Post post);
	
	void deletePost(Long postId);
	
	void updatePost(Post post);
	
	Map<String, Object> findPost(Long postId);
	
	Map<String, Object> findAllPosts();
	
}