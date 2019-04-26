package com.xust.repository;

import com.xust.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ldy
 * @date 2019/4/19 20:01
 */
public interface UserRepository extends CrudRepository<User,Long> {


}
