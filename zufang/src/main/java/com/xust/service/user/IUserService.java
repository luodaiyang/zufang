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
    /**
     *
     * 根据电话号码寻找
     * @author Luo Daiyang
     * @date 2019/5/12 8:56
     * @param [telephone]
     * @return com.xust.entity.User
     */

    User findUserByTelephone(String telephone);
    /**
     * 根据手机号注册用户
     * */
    User addUserByPhone(String telephone);

    /**
     *
     * 修改User指定属性值
     * @author Luo Daiyang
     * @date 2019/5/12 15:52
     * @param [profile, value]
     * @return com.xust.service.ServiceResult
     */
    ServiceResult modifyUserProfile(String profile, String value);

}
