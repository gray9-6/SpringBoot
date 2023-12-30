package com.example.spring6webapp.bootstrap;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository,PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author ajay = new Author("Ajay","Yadav");
        Book java = new Book("Java","1234");

        Author ajaySavedAuthor = authorRepository.save(ajay);
        Book javaSavedBook = bookRepository.save(java);

        /*-----------------------------------------------------------*/
        Author abhay = new Author("Abhay","Yadav");
        Book python = new Book("Python","12344");

        Author abhaySavedAuthor = authorRepository.save(abhay);
        Book pythonSavedBook = bookRepository.save(python);

        /*-------------------------------------------------------------*/
        // now save book to authors, so that , the book we added to author can also be saved in db
        ajaySavedAuthor.getBooks().add(javaSavedBook);
        abhaySavedAuthor.getBooks().add(pythonSavedBook);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Ruby");
        publisher.setCity("Panchkula");
        publisher.setState("Gurgaon");
        publisher.setZipCode("134102");
        publisher.setAddress("pinjore,panchkula,Haryana");
        Publisher savedPublisher = publisherRepository.save(publisher);

        // add publisher to the book
        javaSavedBook.setPublisher(savedPublisher);
        pythonSavedBook.setPublisher(savedPublisher);

        // also save the author  and book to repository
        authorRepository.save(ajaySavedAuthor);
        authorRepository.save(abhaySavedAuthor);
        bookRepository.save(javaSavedBook);
        bookRepository.save(pythonSavedBook);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());

        System.out.println("Author count: " + publisherRepository.count());


    }

    private  Author createAuthor(String firstName, String lastName){
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author;
    }

    private Book createBook(String title, String isbn){
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        return book;
    }
}
