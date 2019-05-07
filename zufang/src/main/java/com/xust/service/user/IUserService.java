package com.xust.service.user;

import com.xust.entity.User;
import com.xust.service.ServiceResult;
import com.xust.web.dto.UserDTO;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 17:20 2019/4/24
 * @modified By:
 */
public interface IUserService {
    User findUserByName(String userName);

    ServiceResult<UserDTO> findById(Long userId);
}
