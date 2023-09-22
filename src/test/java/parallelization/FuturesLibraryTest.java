package parallelization;// This file must *not* be modified!

import org.javagrader.Allow;
import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;

@Grade
public class FuturesLibraryTest {
    private static class MyBook implements FuturesLibrary.Book {
        private String author;
        private ArrayList<String> words = new ArrayList<String>();

        MyBook(String author) {
            this.author = author;
        }

        public MyBook addWord(String word) {
            words.add(word);
            return this;
        }

        @Override
        public String getAuthor() {
            return author;
        }

        @Override
        public int getNumberOfWords() {
            return words.size();
        }

        @Override
        public String getWord(int index) {
            return words.get(index);
        }
    }

    private static class MyLibrary implements FuturesLibrary.Library {
        private ArrayList<FuturesLibrary.Book> books = new ArrayList<FuturesLibrary.Book>();

        MyBook addBook(String author) {
            MyBook book = new MyBook(author);
            books.add(book);
            return book;
        }

        FuturesLibrary.Book addBook(FuturesLibrary.Book book) {
            books.add(book);
            return book;
        }

        @Override
        public int getNumberOfBooks() {
            return books.size();
        }

        @Override
        public FuturesLibrary.Book getBook(int index) {
            return books.get(index);
        }
    }

    public static boolean containsWord(FuturesLibrary.Book book,
                                       String word) {
        for (int i = 0; i < book.getNumberOfWords(); i++) {
            if (book.getWord(i).equals(word)) {
                return true;
            }
        }
        return false;
    }

    @Test
    @Grade(value = 1, cpuTimeout = 100)
    public void testSimpleSequential() {
        MyLibrary library = new MyLibrary();
        library.addBook("Schaus").addWord("Hello").addWord("World").addWord("42");
        library.addBook("Jodogne").addWord("Never").addWord("Gonna").addWord("Give").addWord("You").addWord("Up");
        library.addBook("Sadre").addWord("10").addWord("World").addWord("Hello");

        Predicate<FuturesLibrary.Book> p = (book) -> containsWord(book, "World");
        assertEquals(0, FuturesLibrary.countMatchingBooks(library, p, 0, 0));
        assertEquals(1, FuturesLibrary.countMatchingBooks(library, p, 0, 1));
        assertEquals(1, FuturesLibrary.countMatchingBooks(library, p, 0, 2));
        assertEquals(2, FuturesLibrary.countMatchingBooks(library, p, 0, 3));
        assertEquals(0, FuturesLibrary.countMatchingBooks(library, p, 1, 0));
        assertEquals(0, FuturesLibrary.countMatchingBooks(library, p, 1, 1));
        assertEquals(1, FuturesLibrary.countMatchingBooks(library, p, 1, 2));
        assertEquals(0, FuturesLibrary.countMatchingBooks(library, p, 2, 0));
        assertEquals(1, FuturesLibrary.countMatchingBooks(library, p, 2, 1));

        assertEquals(1, FuturesLibrary.countMatchingBooks(library, (book) -> book.getAuthor().equals("Schaus"),
                0, library.getNumberOfBooks()));
    }

    @Test
    @Grade(value = 1, cpuTimeout = 100)
    public void testSimpleThreads() {
        MyLibrary library = new MyLibrary();
        library.addBook("Schaus").addWord("Hello").addWord("World").addWord("42");
        library.addBook("Jodogne").addWord("Never").addWord("Gonna").addWord("Give").addWord("You").addWord("Up");
        library.addBook("Sadre").addWord("10").addWord("World").addWord("Hello");

        ExecutorService service = Executors.newFixedThreadPool(2);
        try {
            assertEquals(2, FuturesLibrary.countMatchingBooksWithThreads(library, (book) -> containsWord(book, "World"),
                    service, 2));
            assertEquals(1, FuturesLibrary.countMatchingBooksWithThreads(library, (book) -> book.getAuthor().equals(
                    "Schaus"), service, 2));
        } finally {
            service.shutdown();
        }
    }

}
