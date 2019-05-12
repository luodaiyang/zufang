package com.xust.service.house;

import com.xust.service.ServiceResult;
import com.xust.service.search.BaiduMapLocation;
import com.xust.zufangApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 15:40 2019/5/11
 * @modified By:
 */
public class AddressServiceImplTest  extends zufangApplicationTests {

    @Autowired
    private  IAddressService addressService;

    @Test
    public void getBaiduMapLocation() {
        String city="北京";
        String address = "北京市昌平区巩华家园1号楼2单元";
        ServiceResult<BaiduMapLocation> result= addressService.getBaiduMapLocation(city,address);
        Assert.assertTrue(result.isSuccess());
        Assert.assertTrue(result.getResult().getLatitude()>0);
        Assert.assertTrue(result.getResult().getLongitude()>0);
    }
}