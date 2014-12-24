package bzh.medek.server.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import bzh.medek.server.json.JsonResponse;
import bzh.medek.server.json.JsonSimpleResponse;
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
    public List<Book> getAll() {
    	List<Book> b = bookDao.getBooks();
    	LOGGER.info("find "+b.size()+" books in the database");
    	return b;
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
     *  POST /books : create one book
     * 
     * @param id
     * @return
     */
    @POST
    public JsonResponse createOne(JsonBook book) {
    	Book b = new Book();
    	b.setTitle(book.getName());
    	return new JsonSimpleResponse();
    }


    /**
     *  PUT /books/{id} : update one book
     * 
     * @param id
     * @return
     */
    @PUT
    @Path(value = "/{id}")
    public Book updateOne(@PathParam(value = "id") Integer id, JsonBook book) {
    	Book b = bookDao.getBook(id);
    	b.setTitle(book.getName());
    	LOGGER.info("find "+b.getTitle()+" book in the database to update");
    	return b;
    }
    
	
	
}
