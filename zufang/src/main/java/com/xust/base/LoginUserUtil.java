package com.xust.base;


import com.xust.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 14:54 2019/5/3
 * @modified By:
 */
public class LoginUserUtil {
    public  static User load(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal !=null&& principal instanceof User){
            return  (User)principal;
        }
        return null;
    }




    public  static Long getLoginUserId(){
        User user=load();
        if (user==null){
            return -1L;
        }
        return user.getId();
    }
}
