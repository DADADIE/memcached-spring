package com.supersoft;

import com.supersoft.service.MemcachedService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * User:hacker
 * Date:2016/7/14
 * Time:22:21
 * Description:This class is created to ...
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:spring-memcached.xml")
public class MemcachedServiceTest {
    @Resource
    private MemcachedService memcachedService;

    @Test
    public void testSetSession(){
        memcachedService.setSession("001","guanjunpu");
    }

    @Test
    public void testGetSession(){
        Assert.assertEquals("guanjunpu",memcachedService.getSession("001"));
    }
}
