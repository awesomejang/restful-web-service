package com.example.restfulwebservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{ // 다룰 Object타입, PK타입 // Spring Data JPA -> JpaRepository == EntityManager

}
