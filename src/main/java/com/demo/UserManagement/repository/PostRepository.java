package com.demo.UserManagement.repository;

import com.demo.UserManagement.model.Category;
import com.demo.UserManagement.model.Post;
import com.demo.UserManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);

}
