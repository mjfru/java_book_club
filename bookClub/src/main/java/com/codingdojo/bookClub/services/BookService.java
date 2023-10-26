package com.codingdojo.bookClub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookClub.models.Book;
import com.codingdojo.bookClub.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	// Read All
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
	// Read One
	public Book findOneBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	// Create
	public Book createBook(Book newBook) {
		return bookRepository.save(newBook);
	}
	
	// Update
	public Book updateBook(Book updatedBook) {
		return bookRepository.save(updatedBook);
	}
	
	// Delete
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

}
