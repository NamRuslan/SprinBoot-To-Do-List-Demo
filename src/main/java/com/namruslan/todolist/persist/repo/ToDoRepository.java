package com.namruslan.todolist.persist.repo;

import com.namruslan.todolist.persist.entity.ToDo;
import com.namruslan.todolist.repr.ToDoRepr;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    @Query("select new com.namruslan.todolist.repr.ToDoRepr(t) from ToDo t " +
            "where t.user.id = :userId")
    List<ToDoRepr> findToDosByUserId(@Param("userId") Long userId);
}
