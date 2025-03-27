package com.tabalho.primeiro.controllers;

import com.tabalho.primeiro.models.BookModel;
import com.tabalho.primeiro.models.Status;
import com.tabalho.primeiro.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> books = bookService.getAllBooks();
        return books.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable int id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookModel> createBook(@RequestBody BookModel book) {
        return ResponseEntity.status(201).body(bookService.createBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable int id, @RequestBody BookModel bookDetails) {
        return bookService.updateBook(id, bookDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookModel> updateBookStatus(@PathVariable int id, @RequestBody Map<String, String> statusUpdate) {
        try {
            Status newStatus = Status.valueOf(statusUpdate.get("status"));
            return bookService.updateBookStatus(id, newStatus)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable int id) {
        return bookService.getBookById(id).map(book -> {
            bookService.deleteBook(id);
            return ResponseEntity.ok(Map.of("message",
                    String.format("Livro '%s' de %s (ID: %d) foi excluído com sucesso.",
                            book.getTitulo(), book.getAutor(), book.getId())));
        }).orElse(ResponseEntity.status(404).body(Map.of("error", "Livro não encontrado.")));
    }
}
