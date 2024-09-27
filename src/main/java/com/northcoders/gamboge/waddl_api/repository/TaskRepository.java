package com.northcoders.gamboge.waddl_api.repository;

import com.northcoders.gamboge.waddl_api.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}
