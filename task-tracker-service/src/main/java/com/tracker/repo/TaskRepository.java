package com.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
