package org.hibernate.search.hibernate.example.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.hibernate.example.dao.BookDao;
import org.hibernate.search.hibernate.example.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class BookDaoImplTest {
	
	@Resource(name="bookDaoImpl")
	private BookDao bookDao ;
	
	@Test
	public void query(){
		List<Book> list = bookDao.query(5, 5);
		for (Book book : list) {
			System.out.println(book.getName());
		}
	}
	
	
	@Test
	public void search(){
		int start=0;
		int pagesize=5;
		Analyzer analyzer=new PaodingAnalyzer();
		String[] field=new String[]{"name","description","authors.name"};
		List<Book> list = null;
		try {
			list = bookDao.query("实战", start, pagesize, analyzer, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Book book : list) {
			System.out.println("书名:"+book.getName()+"\n描述:"+book.getDescription()+"\n出版日期:"+book.getPublicationDate());
			System.out.println("----------------------------------------------------------");
		}
	}
	
}
