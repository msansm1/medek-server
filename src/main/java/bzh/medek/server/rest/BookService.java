package bzh.medek.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import bzh.medek.server.json.book.JsonBook;
import bzh.medek.server.persistence.dao.BookDAO;
import bzh.medek.server.persistence.entities.Book;

@Stateless
@ApplicationPath("/services")
@Path(value = "/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookService extends Application {

    private static final Logger LOGGER = Logger.getLogger(BookService.class);
    
    @Inject
    BookDAO bookDao;
	
	public BookService () {
	}

    /**
     *  GET /books : retrieve all books
     * 
     * @return
     */
    @GET
    public List<JsonBook> getAll() {
    	List<Book> books = bookDao.getBooks();
    	LOGGER.info("find "+books.size()+" books in the database");
    	ArrayList<JsonBook> lb = new ArrayList<JsonBook>();
    	for (Book b : books) {
    		lb.add(new JsonBook(b.getId(), b.getTitle(), "", ""));
    	}
    	return lb;
    }

    /**
     *  GET /books/{id} : retrieve one book
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public Book getOne(@PathParam(value = "id") Integer id) {
    	Book b = bookDao.getBook(id);
    	LOGGER.info("find "+b.getTitle()+" book in the database");
    	return b;
    }

    /**
     *  POST /movies : create / update one movie
     * 
     * @param id
     * @return
     */
    @POST
    public JsonBook createUpdateOne(JsonBook jb) {
    	JsonBook jbook = jb;
    	if (jb.getId() == null) {
    		Book b = new Book();
    		b.setTitle(jb.getName());
    		bookDao.saveBook(b);
	    	jbook.setId(b.getId());
    	} else {
    		Book b = bookDao.getBook(jb.getId());
    		b.setTitle(jb.getName());
    		bookDao.updateBook(b);
    	}
    	return jbook;
    }
    
	
	
}
