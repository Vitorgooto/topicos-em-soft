package com.tabalho.primeiro.service;

import com.tabalho.primeiro.models.BookModel;
import com.tabalho.primeiro.models.Status;
import com.tabalho.primeiro.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public BookModel createBook(BookModel book) {
        return bookRepository.save(book);
    }

    public Optional<BookModel> updateBook(int id, BookModel bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitulo(bookDetails.getTitulo());
            book.setAutor(bookDetails.getAutor());
            BookModel updatedBook = bookRepository.save(book);
            return updatedBook;
        });
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<BookModel> updateBookStatus(int id, Status status) {
        return bookRepository.findById(id).map(book -> {
            book.setStatus(status);
            return bookRepository.save(book);
        });
    }
}
