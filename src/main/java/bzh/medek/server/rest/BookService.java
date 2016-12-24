package bzh.medek.server.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import bzh.medek.server.conf.Conf;
import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.book.JsonBook;
import bzh.medek.server.json.book.JsonMyBook;
import bzh.medek.server.persistence.dao.BookDAO;
import bzh.medek.server.persistence.dao.BooktypeDAO;
import bzh.medek.server.persistence.dao.CollectionDAO;
import bzh.medek.server.persistence.dao.EditorDAO;
import bzh.medek.server.persistence.dao.LangDAO;
import bzh.medek.server.persistence.dao.StorygenreDAO;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.dao.UserbookDAO;
import bzh.medek.server.persistence.entities.Book;
import bzh.medek.server.persistence.entities.Bookartist;
import bzh.medek.server.persistence.entities.Collection;
import bzh.medek.server.persistence.entities.Userbook;
import bzh.medek.server.persistence.entities.UserbookPK;
import bzh.medek.server.utils.Constants;

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
	@Inject
	Conf conf;
	@Inject
	UserbookDAO userbookDAO;
	@Inject
	UserDAO userDAO;

	public BookService() {
	}

	/**
	 * GET /books : retrieve all books
	 * 
	 * @return
	 */
	@GET
	public List<JsonBook> getAllWithParams(@Context HttpServletRequest request, 
			@QueryParam("from") int from, @QueryParam("limit") int limit,
			@QueryParam("orderBy") String orderBy, @QueryParam("orderDir") String orderDir) {
		List<Book> books = bookDao.getBooksForList(from, limit, orderBy, orderDir);
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
			Userbook myb = userbookDAO.getUserbook(b.getId(), request.getHeader(Constants.HTTP_HEADER_TOKEN));
			lb.add(new JsonBook(b.getId(), b.getTitle(), artistName, artistId,
					(b.getEditorBean() != null) ? b.getEditorBean().getName()
							: "", (b.getEditorBean() != null) ? b
							.getEditorBean().getId() : 0, (b
							.getCollectionBean() != null) ? b
							.getCollectionBean().getName() : "", (b
							.getCollectionBean() != null) ? b
							.getCollectionBean().getId() : 0, b.getCover(), b
							.getDescription(), b.getPublicationdate(), (b
							.getStorygenre() != null) ? b.getStorygenre()
							.getName() : "", (b.getStorygenre() != null) ? b
							.getStorygenre().getId() : 0,
					(b.getBooktype() != null) ? b.getBooktype().getName() : "",
					(b.getBooktype() != null) ? b.getBooktype().getId() : 0, (b
							.getBooktype() != null) ? b.getLangBean().getName()
							: "", (b.getBooktype() != null) ? b.getLangBean()
							.getId() : 0, b.getSeries(), b.getBooknb(), b
							.getIsseriedone(), (myb != null) ? true : false,
					(myb != null) ? myb.getRating() : 0, (myb != null) ? myb
							.getIssigned() : false));
		}
		return lb;
	}

	/**
	 * GET /books/user : retrieve books for one user
	 * 
	 * @return
	 */
	@GET
	@Path(value = "user")
	public List<JsonBook> getUserBooks(@Context HttpServletRequest request, 
			@QueryParam("from") int from, @QueryParam("limit") int limit,
			@QueryParam("orderBy") String orderBy, @QueryParam("orderDir") String orderDir,
			@QueryParam("userId") Integer userId) {
		List<JsonBook> books = bookDao.getUserBooksForList(from, limit, orderBy, orderDir, userId);
		LOGGER.info("find " + books.size() + " books in the database");
		String artistName = "";
		Integer artistId = 0;
		List<Bookartist> bartists = null;
		for (JsonBook b : books) {
			bartists = bookDao.getBookArtists(b.getId());
			if (!bartists.isEmpty()) {
				artistName = bartists.get(0).getArtistBean()
						.getName()
						+ " "
						+ bartists.get(0).getArtistBean()
								.getFirstname();
				artistId = bartists.get(0).getArtistBean().getId();
			} else {
				artistName = "";
				artistId = 0;
			}
			if (bookDao.getBook(b.getId()).getCollectionBean() != null) {
				Collection c = bookDao.getBook(b.getId()).getCollectionBean();
				b.setCollection(c.getName());
				b.setCollectionId(c.getId());
			}
			b.setAuthor(artistName);
			b.setAuthorId(artistId);
		}
		return books;
	}

	/**
	 * GET /books/{id} : retrieve one book
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path(value = "{id}")
	public JsonBook getOne(@Context HttpServletRequest request, @PathParam(value = "id") Integer id) {
		Book b = bookDao.getBook(id);
		LOGGER.info("find " + b.getTitle() + " in the database");
		String artistName = "";
		Integer artistId = 0;
		if (!b.getBookartists().isEmpty()) {
			artistName = b.getBookartists().get(0).getArtistBean()
					.getName()
					+ " "
					+ b.getBookartists().get(0).getArtistBean()
							.getFirstname();
			artistId = b.getBookartists().get(0).getArtistBean().getId();
		}
		Userbook myb = userbookDAO.getUserbook(b.getId(), request.getHeader(Constants.HTTP_HEADER_TOKEN));
		JsonBook jb = new JsonBook().setId(b.getId()).setTitle(b.getTitle()).
				setCover(b.getCover()).setPublicationDate(b.getPublicationdate()).
				setAuthor(artistName).setAuthorId(artistId).setDescription(b.getDescription()).
				setSeries(b.getSeries()).setIsSerieDone(b.getIsseriedone()).setBookNb(b.getBooknb());
		if (b.getEditorBean() != null) {
			jb.setEditor(b.getEditorBean().getName())
			.setEditorId(b.getEditorBean().getId());
		} else {
			jb.setEditor("").setEditorId(null);
		}
		if (b.getCollectionBean() != null) {
			jb.setCollection(b.getCollectionBean().getName())
			.setCollectionId(b.getCollectionBean().getId());
		} else {
			jb.setCollection("").setCollectionId(null);
		}
		if (b.getStorygenre() != null) {
			jb.setGenre(b.getStorygenre().getName()).
			setGenreId(b.getStorygenre().getId());
		} else {
			jb.setGenre("").setGenreId(null);
		}
		if (b.getBooktype() != null) {
			jb.setType(b.getBooktype().getName()).
			setTypeId(b.getBooktype().getId());
		} else {
			jb.setType("").setTypeId(null);
		}
		if (b.getLangBean() != null) {
			jb.setLang(b.getLangBean().getName()).
			setLangId(b.getLangBean().getId());
		} else {
			jb.setLang("").setLangId(null);
		}
		if (myb != null) {
			jb.setMycollec(true).setRating(myb.getRating()).setSigned(myb.getIssigned());
		} else {
			jb.setMycollec(false).setRating(0).setSigned(false);
		}
		return jb;
	}

	/**
	 * POST /books : create / update one book
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
	 * POST : upload new cover for book
	 * 
	 * @param newcover
	 * @return
	 */
	@POST
	@Path("{id}/coverupload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadAttach(@PathParam("id") Integer id,
			MultipartFormDataInput newcover) {
		Map<String, List<InputPart>> uploadForm = newcover.getFormDataMap();
		// Get file data to save
		List<InputPart> inputParts = uploadForm.get("file");
		String filename = null;
		for (InputPart inputPart : inputParts) {
			// convert the uploaded file to inputstream and write it to disk
			InputStream inputStream = null;
			OutputStream out = null;
			try {
				inputStream = inputPart.getBody(InputStream.class, null);
				List<String> contDisp = inputPart.getHeaders().get(
						"Content-Disposition");
				for (String cd : contDisp) {
					if (cd.contains("filename")) {
						filename = "cover.jpg";
						LOGGER.info("FILENAME : " + filename);
					}
				}
				String path = conf.getBookFS() + id + "/";
				File pathtest = new File(path);
				if (!pathtest.exists()) {
					if (!pathtest.mkdirs()) {
						LOGGER.error("While saving cover : "
								+ "unable to create repository tmp dir => "
								+ path);
					}
				}
				File up = new File(path + filename);
				if (!up.createNewFile()) {
					if (up.exists()) {
						up.delete();
						if (!up.createNewFile()) {
							LOGGER.error("While saving cover : "
									+ "unable to overwrite existing file => "
									+ up.getAbsolutePath());
						}
					} else {
						LOGGER.error("While saving cover : "
								+ "unable to create new file => "
								+ up.getAbsolutePath());
					}
				}
				out = new FileOutputStream(up);

				int read = 0;
				byte[] bytes = new byte[2048];
				while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				inputStream.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				LOGGER.error("While saving cover : ", e);
				return Response.ok(new JsonSimpleResponse(false),
						MediaType.APPLICATION_JSON).build();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						LOGGER.error(
								"While saving cover - closing inputstream : ",
								e);
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						LOGGER.error(
								"While saving cover - closing outputstream : ",
								e);
					}
				}
			}
		}
		return Response.ok(new JsonSimpleResponse(true),
				MediaType.APPLICATION_JSON).build();
	}

	/**
	 * POST /addtocollec : add book to user's collection
	 * 
	 * @return
	 */
	@POST
	@Path("addtocollec")
	public Response addToCollection(JsonMyBook book) {
		Userbook ub = new Userbook();
		UserbookPK ubid = new UserbookPK();
		ubid.setBook(book.getBookId().intValue());
		ubid.setUser(book.getUserId().intValue());
		ub.setId(ubid);
		ub.setBookBean(bookDao.getBook(book.getBookId()));
		ub.setUserBean(userDAO.getUser(book.getUserId()));
		ub.setIssigned(book.getSigned());
		ub.setComment(book.getComment());
		ub.setRating(book.getRating());
		userbookDAO.saveUserbook(ub);
		return Response.ok(new JsonSimpleResponse(true),
				MediaType.APPLICATION_JSON).build();
	}

}
