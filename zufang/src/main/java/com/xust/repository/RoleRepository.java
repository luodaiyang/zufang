package com.xust.repository;


import com.xust.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色数据DAO
 * @author: Luo Daiyang
 * @description:
 * @date Created in 19:14 2019/4/24
 * @modified By:
 */
public interface RoleRepository extends CrudRepository<Role,Long> {
    List<Role> findRolesByUserId(Long userId);
    Role findRoleByUserId(Long userId);
}
