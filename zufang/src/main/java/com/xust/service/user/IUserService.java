package com.xust.service.user;

import com.xust.entity.User;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 17:20 2019/4/24
 * @modified By:
 */
public interface IUserService {
    User findUserByName(String userName);
}
