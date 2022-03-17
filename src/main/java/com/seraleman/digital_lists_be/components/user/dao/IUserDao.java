package com.seraleman.digital_lists_be.components.user.dao;

import com.seraleman.digital_lists_be.components.user.User;

import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {

}
