package com.xust.entity;



import com.xust.repository.UserRepository;
import com.xust.zufangApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;




/**
 * @author ldy
 * @date 2019/4/19 20:07
 */

public class UserRepositoryTest extends zufangApplicationTests {
   @Autowired
   private UserRepository userRepository;
    @Test
    public  void testFindOne(){
        User user=userRepository.findOne(2L);

        Assert.assertEquals("admin",user.getName());
        System.out.println("mysqltest");
        System.out.println(user);
    }
}
