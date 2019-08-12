package com.caoshaoshuai.domain.test;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
//JSON方式得测试类
@ContextConfiguration(locations="classpath:spring-beans2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTestHash {
	
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
//测试
	@Test
	public void test1() {
		
		Map<String,User> map = new HashMap<>();
		

		//循环
		for (int i = 0; i < 100000; i++) {
			String num="13";
			
			
			for (int j = 0; j < 9; j++) {
				int k = RandomUitl.random(0, 9);
				num+=k;
						
			}
			
			map.put("u_"+i, new User(i, StringUtil.randomChineseString(3), StringUtil.generateChineseName(), num, RandomUitl.randomString(9), DateUtil.randomDate(new Date())));
			
			
		}	
		long start = System.currentTimeMillis();
		//加入扫redis仓库
		redisTemplate.opsForHash().putAll("user", map);
		
		long end = System.currentTimeMillis();
		System.out.println("JSON时间是"+(end-start));
		
		
		
		
	}
	

}
