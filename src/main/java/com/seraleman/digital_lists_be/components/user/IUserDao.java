package com.seraleman.digital_lists_be.components.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {

    List<User> findAllByReasonId(Long reasonId);
}
