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
import bzh.medek.server.persistence.dao.BooktypeDAO;
import bzh.medek.server.persistence.dao.CollectionDAO;
import bzh.medek.server.persistence.dao.EditorDAO;
import bzh.medek.server.persistence.dao.LangDAO;
import bzh.medek.server.persistence.dao.StorygenreDAO;
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
	@Inject
	BooktypeDAO booktypeDAO;
	@Inject
	StorygenreDAO storygenreDAO;
	@Inject
	EditorDAO editorDAO;
	@Inject
	CollectionDAO collectionDAO;
	@Inject
	LangDAO langDAO;

	public BookService() {
	}

	/**
	 * GET /books : retrieve all books
	 * 
	 * @return
	 */
	@GET
	public List<JsonBook> getAll() {
		List<Book> books = bookDao.getBooks();
		LOGGER.info("find " + books.size() + " books in the database");
		ArrayList<JsonBook> lb = new ArrayList<JsonBook>();
		String artistName = "";
		Integer artistId = 0;
		for (Book b : books) {
			if (!b.getBookartists().isEmpty()) {
				artistName = b.getBookartists().get(0).getArtistBean()
						.getName()
						+ " "
						+ b.getBookartists().get(0).getArtistBean()
								.getFirstname();
				artistId = b.getBookartists().get(0).getArtistBean().getId();
			} else {
				artistName = "";
				artistId = 0;
			}
			lb.add(new JsonBook(b.getId(), b.getTitle(), artistName, artistId, (b
					.getEditorBean() != null) ? b.getEditorBean().getName()
					: "", (b.getEditorBean() != null) ? b.getEditorBean()
					.getId() : 0, (b.getCollectionBean() != null) ? b
					.getCollectionBean().getName() : "",
					(b.getCollectionBean() != null) ? b.getCollectionBean()
							.getId() : 0, b.getCover(), b.getDescription(), b
							.getPublicationdate(),
					(b.getStorygenre() != null) ? b.getStorygenre().getName()
							: "", (b.getStorygenre() != null) ? b
							.getStorygenre().getId() : 0,
					(b.getBooktype() != null) ? b.getBooktype().getName() : "",
					(b.getBooktype() != null) ? b.getBooktype().getId() : 0, (b
							.getBooktype() != null) ? b.getLangBean().getName()
							: "", (b.getBooktype() != null) ? b.getLangBean()
							.getId() : 0, b.getSeries(), b.getBooknb(), b
							.getIsseriedone()));
		}
		return lb;
	}

	/**
	 * GET /books/{id} : retrieve one book
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "/{id}")
	public JsonBook getOne(@PathParam(value = "id") Integer id) {
		Book b = bookDao.getBook(id);
		LOGGER.info("find " + b.getTitle() + " book in the database");
		String artistName = "";
		Integer artistId = 0;
		if (!b.getBookartists().isEmpty()) {
			artistName = b.getBookartists().get(0).getArtistBean().getName()
					+ " "
					+ b.getBookartists().get(0).getArtistBean().getFirstname();
			artistId = b.getBookartists().get(0).getArtistBean().getId();
		}
		return new JsonBook(b.getId(), b.getTitle(), artistName, artistId,
				(b.getEditorBean() != null) ? b.getEditorBean().getName() : "",
				(b.getEditorBean() != null) ? b.getEditorBean().getId() : 0,
				(b.getCollectionBean() != null) ? b.getCollectionBean()
						.getName() : "", (b.getCollectionBean() != null) ? b
						.getCollectionBean().getId() : 0, b.getCover(),
				b.getDescription(), b.getPublicationdate(),
				(b.getStorygenre() != null) ? b.getStorygenre().getName() : "",
				(b.getStorygenre() != null) ? b.getStorygenre().getId() : 0,
				(b.getBooktype() != null) ? b.getBooktype().getName() : "",
				(b.getBooktype() != null) ? b.getBooktype().getId() : 0,
				(b.getBooktype() != null) ? b.getLangBean().getName() : "",
				(b.getBooktype() != null) ? b.getLangBean().getId() : 0,
				b.getSeries(), b.getBooknb(), b.getIsseriedone());
	}

	/**
	 * POST /movies : create / update one movie
	 * 
	 * @param id
	 * @return
	 */
	@POST
	public JsonBook createUpdateOne(JsonBook jb) {
		JsonBook jbook = jb;
		if (jb.getId() == null) {
			Book b = new Book();
			b.setTitle(jb.getTitle());
			b.setBooknb(jb.getBookNb());
			b.setDescription(jb.getDescription());
			b.setCover(jb.getCover());
			b.setIsseriedone(jb.getIsSerieDone());
			b.setSeries(jb.getSeries());
			b.setPublicationdate(jb.getPublicationDate());
			if (jb.getEditorId() != null) {
				b.setEditorBean(editorDAO.getEditor(jb.getEditorId()));
			}
			if (jb.getCollectionId() != null) {
				b.setCollectionBean(collectionDAO.getCollection(jb
						.getCollectionId()));
			}
			if (jb.getLangId() != null) {
				b.setLangBean(langDAO.getLang(jb.getLangId()));
			}
			if (jb.getTypeId() != null) {
				b.setBooktype(booktypeDAO.getBooktype(jb.getTypeId()));
			}
			if (jb.getGenreId() != null) {
				b.setStorygenre(storygenreDAO.getStorygenre(jb.getGenreId()));
			}
			bookDao.saveBook(b);
			jbook.setId(b.getId());
		} else {
			Book b = bookDao.getBook(jb.getId());
			b.setTitle(jb.getTitle());
			b.setBooknb(jb.getBookNb());
			b.setDescription(jb.getDescription());
			b.setCover(jb.getCover());
			b.setIsseriedone(jb.getIsSerieDone());
			b.setSeries(jb.getSeries());
			b.setPublicationdate(jb.getPublicationDate());
			if (jb.getEditorId() != null) {
				b.setEditorBean(editorDAO.getEditor(jb.getEditorId()));
			}
			if (jb.getCollectionId() != null) {
				b.setCollectionBean(collectionDAO.getCollection(jb
						.getCollectionId()));
			}
			if (jb.getLangId() != null) {
				b.setLangBean(langDAO.getLang(jb.getLangId()));
			}
			if (jb.getTypeId() != null) {
				b.setBooktype(booktypeDAO.getBooktype(jb.getTypeId()));
			}
			if (jb.getGenreId() != null) {
				b.setStorygenre(storygenreDAO.getStorygenre(jb.getGenreId()));
			}
			bookDao.updateBook(b);
		}
		return jbook;
	}

	/**
	 * GET /books/user/{id} : retrieve books for one user
	 * 
	 * @param id
	 *            - user ID
	 * @return
	 */
	@GET
	@Path(value = "/user/{id}")
	public List<JsonBook> getUserBooks(@PathParam(value = "id") Integer id) {
		return bookDao.getUsersBooks(id);
	}

}
