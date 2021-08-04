package com.IBA.SERVER;

import com.IBA.SERVER.mapper.dbMapper;
import com.IBA.SERVER.domain.bookListsTableDTO;
import com.IBA.SERVER.service.rootCalculation;
import com.IBA.SERVER.controller.orderPocket;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private dbMapper DBMapper;
	/*
	@Test
	void contextLoads(){}

	@Test
	public void testSqlSession() throws Exception{
		System.out.println(sqlSession.toString());
	}
*/
	@Test
	public void testgetLoc() throws Exception{
		int loc = DBMapper.get_bookUID(121);
		System.out.println("location : "+loc);
	}

}
