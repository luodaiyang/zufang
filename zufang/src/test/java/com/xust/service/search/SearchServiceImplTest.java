package com.xust.service.search;

import com.xust.service.ServiceMultiResult;
import com.xust.web.form.RentSearch;
import com.xust.zufangApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: Luo Daiyang
 * @description:
 * @date Created in 18:40 2019/5/8
 * @modified By:
 */
public class SearchServiceImplTest  extends zufangApplicationTests {
    @Autowired
    private  ISearchService searchService;
    @Test
    public void index() {
        Long targetHouseId=15L;
      boolean susses=  searchService.index(targetHouseId);
        Assert.assertTrue(susses);
    }

    @Test
    public  void remove(){
        Long houseId=15L;
        boolean susses=searchService.remove(houseId);

    }
    @Test
    public  void query(){
        RentSearch rentSearch=new RentSearch();
        rentSearch.setCityEnName("bj");
        rentSearch.setStart(0);
        rentSearch.setSize(8);

        ServiceMultiResult<Long> serviceResult=searchService.query(rentSearch);
        Assert.assertEquals(4,serviceResult.getTotal());

    }
}