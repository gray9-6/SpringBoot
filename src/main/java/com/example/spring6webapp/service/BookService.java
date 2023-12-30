package com.example.spring6webapp.service;

import com.example.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
