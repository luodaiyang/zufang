package com.xust.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.xust.zufangApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 9:45 2019/4/28
 * @modified By:
 */
public class QiNiuServiceImplTest extends zufangApplicationTests {
    @Autowired
     private IQiNiuService qiNiuService;
    @Test
    public void delete() {
        String key= "FltOn4iHNjvDh6c18Xlgr3a-mf7b";
        try {
        Response response= qiNiuService.delete(key);
        Assert.assertTrue(response.isOK());

        } catch (QiniuException e) {
            e.printStackTrace();
        }

    }
}