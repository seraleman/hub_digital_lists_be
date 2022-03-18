package com.seraleman.digital_lists_be.components.user;

import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {

}
