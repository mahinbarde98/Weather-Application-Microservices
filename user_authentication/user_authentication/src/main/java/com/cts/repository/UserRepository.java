package com.cts.repository;


import com.cts.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials,String> {

    @Query("select u from UserCredentials u where u.username= :username and u.password= :password")
    UserCredentials ValideUser(@Param("username") String username,@Param("password") String password);

}
