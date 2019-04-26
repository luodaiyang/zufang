package com.xust.service;

import com.xust.entity.User;
import com.xust.zufangApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 11:35 2019/4/26
 * @modified By:
 */
public class IUserServiceTest  extends zufangApplicationTests {
    @Autowired
   private IUserService iUserService;
    @Test
    public void findUserByName() {
        User user=iUserService.findUserByName("admin");
        System.out.println(user);
    }
}