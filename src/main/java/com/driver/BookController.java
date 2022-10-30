package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody(required = true) Book book){
        // Your code goes here.
//        id++;
        book.setId(this.id++);
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){

        for(int i=0; i<bookList.size(); i++){
            if(bookList.get(i).getId() == id){
                return new ResponseEntity<>(bookList.get(i), HttpStatus.FOUND);

            }
        }
        return null;
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public void deleteBookById(@PathVariable("id") Integer id){
        for(int i=0; i<bookList.size(); i++){
            if(bookList.get(i).getId() == id){
                bookList.remove(bookList.get(i));
            }
        }
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("get-all-books")
    public List<Book> getAllBooks(){
        return bookList;
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("delete-all-books")
    public void deleteAllBooks(){

        bookList.removeAll(bookList);
        this.id = 1;
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("get-book-by-author")
    public ResponseEntity<Book> getBookByAuthor(@RequestParam("author") String author){

        for(int i=0; i<bookList.size(); i++){
            if(bookList.get(i).getAuthor().equals(author)){
                return new ResponseEntity<>(bookList.get(i), HttpStatus.FOUND);
            }
        }
        return null;
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("get-book-by-genre")
    public ResponseEntity<Book> getBooksByGenre(@RequestParam("genre") String genre){
        for(int i=0; i<bookList.size(); i++){
            if(bookList.get(i).getGenre().equals(genre)){
                return new ResponseEntity<>(bookList.get(i), HttpStatus.FOUND);
            }
        }
        return null;
    }
}
