package com.test.xin;

import static org.junit.Assert.*;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.bean.Question;

public class QuestionTest {
	Question qst = new Question();


//	@Test
//	public void testAddOneQuestion() {
//		qst.setTitle("是否出现了高温现象");
//		qst.setPossibleAsw("是;否");
//		qst.setNormalAsw("否");
//		qst.setRegion_id(1);
//		qst.setGener_id(1);
//		try {
//			assertEquals(1, Question.addOneQuestion(qst));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testDeleteOneQuestion() {
		try {
			assertEquals(1, Question.deleteOneQuestion("4"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	public void testGetAllQuestion() {
//		try {
//			assertNotEquals(Question.getAllQuestion("1"),new ArrayList<Question>());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testGetAllQuestion2() {
//		try {
//			assertNotEquals(Question.getAllQuestion("1"), new Question());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetOneQuestion() {
//		try {
//			assertNotEquals(Question.getOneQuestion("1"),new Question());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testChangeOneQuestion(){
//		qst.setId("4");
//		qst.setTitle("是否出现了高温现象?");
//		qst.setPossibleAsw("是;否");
//		qst.setNormalAsw("否");
//		qst.setRegion_id(1);
//		qst.setGener_id(1);
//		try {
//			assertEquals(1, Question.changeOneQuestion(qst));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
}
