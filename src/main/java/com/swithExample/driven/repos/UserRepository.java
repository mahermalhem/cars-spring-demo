package com.swithExample.driven.repos;

import com.swithExample.driven.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query(value = "select distinct * from user u " +
            "where u.username=:username " +
            "and u.status=0", nativeQuery = true)
    User getOneByUsername(String username);

    @Query(value = "select distinct * from user u " +
            "where u.status = 0 " +
            "order by u.username,u.email,u.role", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "select distinct * from user u " +
            "where u.email=:email " +
            "and u.status=0", nativeQuery = true)
    User getOneByEmail(String email);

    boolean existsByUsername(String username);
}
