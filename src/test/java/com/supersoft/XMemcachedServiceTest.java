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
public class XMemcachedServiceTest {

    @Resource
    private MemcachedService xMemcachedService;

    @Test
    public void testSetSession(){
        xMemcachedService.setSession("003","guanxiaoguan");
    }

    @Test
    public void testGetSession(){
        Assert.assertEquals("guanxiaoguan",xMemcachedService.getSession("001"));
    }
}
