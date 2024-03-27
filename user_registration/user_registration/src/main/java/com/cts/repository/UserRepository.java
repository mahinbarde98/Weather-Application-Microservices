package com.cts.repository;

import com.cts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

//    @Query(value="select u from User u where u.userId :username and u.userPassword :password")
//    Optional<User> validateUser(@Param("userId") String userId, @Param("userPassword") String userPassword);

    User findByUsernameAndPassword(String username,String password);

    @Query("select u from User u where u.username= :username and u.password= :password")
    User ValideUser(@Param("username") String username,@Param("password") String password);
}
