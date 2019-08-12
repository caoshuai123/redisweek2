package com.caoshaoshuai.domain.test;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caoshaoshuai.test.domain.User;

import caoshaoshuai.week2.utils.DateUtil;
import caoshaoshuai.week2.utils.RandomUitl;
import caoshaoshuai.week2.utils.StringUtil;
//jdk方式得测试类
@ContextConfiguration(locations="classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {
	
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Test
	public void test1() {
		
		
		//创建list集合
		List<User> list = new ArrayList<>();
		
		for (int i = 0; i < 100000; i++) {
			String num="13";
			
			
			for (int j = 0; j < 9; j++) {
				int k = RandomUitl.random(0, 9);
				num+=k;
						
			}
			
			list.add(i, new User(i, StringUtil.randomChineseString(3), StringUtil.generateChineseName(), num, RandomUitl.randomString(9), DateUtil.randomDate(new Date())));
		}
		
		long start = System.currentTimeMillis();
		//加入扫redis仓库
		for (User u : list) {
			redisTemplate.opsForValue().set("u_"+u.getId(), u);
		}
		long end = System.currentTimeMillis();
		System.out.println("JDK时间是"+(end-start));
		
		
		
	}
	

}
