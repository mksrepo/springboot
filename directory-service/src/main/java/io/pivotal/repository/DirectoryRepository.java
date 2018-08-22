package io.pivotal.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.domain.Directory;

public interface DirectoryRepository extends CrudRepository<Directory, Long> {

}