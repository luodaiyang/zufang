package com.xust.repository;

import com.xust.entity.Role;
import com.xust.zufangApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 11:32 2019/4/26
 * @modified By:
 */
public class RoleRepositoryTest  extends zufangApplicationTests {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void findRoleByUserId() {
        Role role=roleRepository.findRoleByUserId(2L);
        System.out.println(role);
    }
}