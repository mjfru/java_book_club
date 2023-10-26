package com.codingdojo.bookClub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.bookClub.models.Book;
import com.codingdojo.bookClub.models.LoginUser;
import com.codingdojo.bookClub.models.User;
import com.codingdojo.bookClub.services.BookService;
import com.codingdojo.bookClub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private BookService bookService;
	
	 // Registration and Login-related Routes:
	 
	 // Render the Registration Page:
	 @GetMapping("/")
	 public String login(Model model) {
		 model.addAttribute("newUser", new User());
		 model.addAttribute("newLogin", new LoginUser());
		 return "login.jsp";
	 }
	 
	 // Register a new user:
	 @PostMapping("/register")
	 public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		 userService.register(newUser, result);
		 if (result.hasErrors()) {
			 model.addAttribute("newLogin", new LoginUser());
			 return "login.jsp";
		 } else {
			 session.setAttribute("userId", newUser.getId());
			 return "redirect:/books";
		 }
	 }
	 
	 // Logging in an existing user:
	 @PostMapping("/login")
	 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		 User user = userService.login(newLogin, result);
		 if (result.hasErrors()) {
			 model.addAttribute("newUser", new User());
			 return "login.jsp";
		 } else {
			 session.setAttribute("userId", user.getId());
			 return "redirect:/books";
		 }
	 }
	 
	 // Logging out a user:
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 session.setAttribute("userId", null);
		 return "redirect:/";
	 }
	
	 // Render the Dashboard
	 @GetMapping("/books")
	 public String dashboard(Model model, HttpSession session) {
		 Long userId = (Long) session.getAttribute("userId");
		 if (userId == null) {
			 return "redirect:/";
		 }
		 User user = userService.findById(userId);
		 model.addAttribute("user", user);
		 List<Book> allBooks = bookService.allBooks();
		 model.addAttribute("allBooks", allBooks);
		 return "books.jsp";
	 }
	 
	 // Book-related Routes:
	 @GetMapping("/book/{id}")
	 public String viewBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		 Book book = bookService.findOneBook(id);
		 model.addAttribute("book", book);
		 Long userId = (long) session.getAttribute("userId");
		 model.addAttribute("userId", userId);
		 return "oneBook.jsp";
	 }
	 
	 // Go through this line-by-line tomorrow 10/23
	 @GetMapping("/books/new")
	 public String addBook(@ModelAttribute("newBook") Book newBook, Model model, HttpSession session) {
		 Long userId = (Long) session.getAttribute("userId");
		 if (userId == null) {
			 return "redirect:/";
		 }
		 User currentUser = userService.findById((Long)session.getAttribute("userId"));
		 model.addAttribute("currentUser", currentUser);
		 return "newBook.jsp";
	 }
	 
	 @PostMapping("/books/new")
	 public String createBook(@Valid @ModelAttribute("newBook") Book newBook, BindingResult result, HttpSession session) {
		 if (result.hasErrors()) {
			 return "newBook.jsp";
		 } else {
			 Long userId = (Long) session.getAttribute("userId");
			 if (userId == null) {
				 return "redirect:/";
			 }
			 User user = userService.findById(userId);
			 newBook.setUser(user);
			 bookService.createBook(newBook);
			 return "redirect:/books";
		 }	 
	 }
	 
	 // Editing Books:
	 @GetMapping("/book/edit/{id}")
	 public String editBookPage(Model model, @PathVariable("id") Long id) {
		 Book book = bookService.findOneBook(id);
		 model.addAttribute("book", book);
		 return "editBook.jsp";
	 }
	 
	 @PutMapping("/book/edit/{id}")
	 public String editBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		 if (result.hasErrors()) {
			 model.addAttribute("book", book);
			 return "editBook.jsp";
		 } else {

			 bookService.updateBook(book);
			 return "redirect:/books";
		 }
	 }
	 
	 // Deleting Books:
	 @DeleteMapping("/book/delete/{id}")
	 public String deleteBook(@PathVariable("id") Long id) {
		 bookService.deleteBook(id);
		 return "redirect:/books";
	 }
	 
}
