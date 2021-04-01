package com.namruslan.todolist.persist.repo;

import com.namruslan.todolist.persist.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
