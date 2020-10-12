package com.gnnu.dao;

import com.gnnu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, Serializable {
    @Query("select t from User t where t.username=?1")
    User getUserByName(String username);
}
