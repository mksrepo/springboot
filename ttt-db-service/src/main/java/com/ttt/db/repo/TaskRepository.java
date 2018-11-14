package com.ttt.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttt.db.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
