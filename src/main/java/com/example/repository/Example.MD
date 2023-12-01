example:
package Application.Repository;

import Application.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * In this JPA Repository interface, we will be exploring JPA's ability to infer queries from method names. We'll
 * refer to these as query methods. While JPA does allow the developer to include their own database queries, many
 * operations are simple enough that they can be adequately described using a specific method name.
 *
 * Spring can perform different forms of filtering or aggregation based off of the fields provided in your entity.
 * The book class has the following fields provided:
 *     private long id;
 *     private long isbn;
 *     private String title;
 *     private String author;
 *     private boolean available;
 *     private int pages;
 *     private Timestamp dateAdded;
 *     private Timestamp lastDateWithdrawn;
 *
 *     The test cases for this lab will attempt to identify the query methods you've written and run them.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * This will become a usable method when the BookRepository is injected into one of your classes.
     * @param isbn a long that identifies distinct books.
     * @return the book with a particular ISBN
     */
    Book findBookByIsbn(long isbn);
    /**
     * This query will return a List of books that match a certain Author string for the Author field.
     * @param author
     * @return
     */
    List<Book> findBooksByAuthor(String author);

    /**
     * More complex clauses, such as an 'AND' statement, can be written as part of a query method.
     * @param author
     * @param dateAdded
     * @return
     */
    List<Book> findBooksByAuthorAndDateAdded(String author, Timestamp dateAdded);
FOR ACCOUNT REPOSITORY:
    /**
     * TODO: Retrieve a book by its title. You may assume that titles are unique and that a single Book entity should
     * be returned, so the return type will be Book.
     */
    Book findBookByTitle(String title);
FOR MESSAGE REPOSITORY
    /**
     * TODO: Retrieve books by their availability using their available field. The return type will be List<Book>.
     */
    List<Book> findBooksByAvailable(boolean available);

    /**
     * TODO: Retrieve books by their dateAdded OR their lastDateWithdrawn.
     */
    List<Book> findBooksByDateAddedOrLastDateWithdrawn(Timestamp dateAdded, Timestamp lastDateWithdrawn);

}