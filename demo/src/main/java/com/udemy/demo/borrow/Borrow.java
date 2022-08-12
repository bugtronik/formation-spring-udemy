package com.udemy.demo.borrow;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.udemy.demo.book.Book;
import com.udemy.demo.user.User;

@Entity
public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User borrower;
	
	@ManyToOne
	private User lender;
	
	@ManyToOne
	private Book book;
	
	private LocalDate askDate;
	private LocalDate closeDate;
	
	public LocalDate getAskDate() {
		return askDate;
	}
	public void setAskDate(LocalDate askDate) {
		this.askDate = askDate;
	}
	public LocalDate getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}
	public User getBorrower() {
		return borrower;
	}
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}
	public User getLender() {
		return lender;
	}
	public void setLender(User lender) {
		this.lender = lender;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

}
