package com.xust.service.house;


import java.io.File;
import java.io.InputStream;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
/**
 * 七牛云服务
 * @author: Luo Daiyang
 * @description:
 * @date Created in 18:28 2019/4/27
 * @modified By:
 */
public interface IQiNiuService {
    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;
}
