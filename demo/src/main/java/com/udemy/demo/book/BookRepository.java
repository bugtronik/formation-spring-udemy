package com.udemy.demo.book;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	List<Book> findByStatusAndUserIdNotAndDeletedFalse(BookStatus status, Integer userId);
	List<Book> findByUserIdAndDeletedFalse(Integer userId);
}
