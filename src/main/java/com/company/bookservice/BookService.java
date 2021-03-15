package com.company.bookservice;

import com.company.entities.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final Map<Long, Book> globalBookMap = new HashMap<>();

    public void addBook(Book newBook) {
        globalBookMap.put(newBook.getId(), newBook);
    }

    public void updateBookMap(Book updatedBook, Long place) {
        globalBookMap.remove(place);
        globalBookMap.put(place, updatedBook);
    }

    public void deleteBookFromMap(Long place) {
        globalBookMap.remove(place);
    }

    public Book getBookFromMap(Long id) {
        return globalBookMap.get(id);
    }

    public Map<Long, Book> returnAllBooks() {
        return globalBookMap;
    }

    public Map<Long, Book> findByAuthor(String author) {

        return globalBookMap.entrySet().stream()
                .filter(books -> books.getValue().getAuthor().equals(author))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Long, Book> findByPriceAndHigher(String price) {
       return globalBookMap.entrySet().stream()
                .filter(books -> Double.parseDouble(books.getValue().getPrice()) >= Double.parseDouble(price))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public Map<Long, Book> findByPriceAndLower(String price) {
        return globalBookMap.entrySet().stream()
                .filter(books -> Double.parseDouble(books.getValue().getPrice()) <= Double.parseDouble(price))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
