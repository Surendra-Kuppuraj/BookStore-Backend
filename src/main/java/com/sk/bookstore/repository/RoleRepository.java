package com.sk.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.sk.bookstore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
