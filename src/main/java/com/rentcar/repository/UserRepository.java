package com.rentcar.repository;

import com.rentcar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long>, JpaRepository<User, Long>  {


     User findByLogin(String login);

     List<User> findByNameContainingIgnoreCase(String name);

     List<User> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);

     boolean existsByLogin(String login);

     List<User> findByIsDeletedFalse();

     Optional<User> findByIdAndIsDeletedFalse(Long id);




}
