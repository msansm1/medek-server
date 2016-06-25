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
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import bzh.medek.server.json.friend.JsonLoan;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.dao.BookDAO;
import bzh.medek.server.persistence.dao.LoanDAO;
import bzh.medek.server.persistence.dao.MovieDAO;
import bzh.medek.server.persistence.dao.TvshowDAO;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.entities.Loan;

@Stateless
@ApplicationPath("/services")
@Path(value = "/loans")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoanService extends Application {

    private static final Logger LOGGER = Logger.getLogger(LoanService.class);
    
    @Inject
    LoanDAO loanDAO;
    @Inject
    UserDAO userDAO;
    @Inject
    AlbumDAO albumDAO;
    @Inject
    BookDAO bookDAO;
    @Inject
    MovieDAO movieDAO;
    @Inject
    TvshowDAO tvshowDAO;
	
	public LoanService () {
	}

    /**
     *  GET /loans/loanto/{userId} : retrieve all loans to an user
     * 
     * @param userId
     * @return
     */
    @GET
    @Path(value = "/loanto/{userId}")
    public List<JsonLoan> getLoansFor(@PathParam(value = "userId") Integer userId) {
    	List<Loan> ll = userDAO.getUser(userId).getLoans2();
    	return getJsonLoansFromLoans(userId, ll);
    }

    /**
     *  GET /loans/borrow/{userId} : retrieve all borrows of an user
     * 
     * @param userId
     * @return
     */
    @GET
    @Path(value = "/borrow/{userId}")
    public List<JsonLoan> getLends(@PathParam(value = "userId") Integer userId) {
    	List<Loan> ll = userDAO.getUser(userId).getLoans1();
    	return getJsonLoansFromLoans(userId, ll);
    }

    /**
     *  GET /loans/{id} : retrieve one loan
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "{id}")
    public Response getOne(@PathParam(value = "id") Integer id) {
    	Loan l = loanDAO.getLoan(id);
    	if (l == null) {
    		return Response.status(Response.Status.NOT_FOUND)
    				.entity("No loan found").build();
    	}
    	JsonLoan jl = new JsonLoan();
    	jl.setId(l.getId());
    	jl.setStartDate(l.getStartdate());
    	jl.setEndDate(l.getEnddate());
    	jl.setUserId(l.getUser2().getId());
    	jl.setUserLogin(l.getUser2().getLogin());
    	jl.setFriendId(l.getUser1().getId());
    	jl.setFriendLogin(l.getUser1().getLogin());
    	if (l.getAlbumBean() != null) {
    		jl.setAlbum(l.getAlbumBean().getTitle());
    		jl.setAlbumId(l.getAlbumBean().getId());
    	} else if (l.getBookBean() != null) {
    		jl.setBook(l.getBookBean().getTitle());
    		jl.setBookId(l.getBookBean().getId());
    	} else if (l.getMovieBean() != null) {
    		jl.setMovie(l.getMovieBean().getTitle());
    		jl.setMovieId(l.getMovieBean().getId());
    	} else if (l.getTvshowBean() != null) {
    		jl.setTvshow(l.getTvshowBean().getTitle());
    		jl.setTvshowId(l.getTvshowBean().getId());
    	}
    	return Response.status(Response.Status.OK)
				.entity(jl).build();
    }

    /**
     *  POST /loans/{userId} : save / update loan
     * 
     * @param userId
     * @return
     */
    @POST
    @Path(value = "/{userId}")
    public JsonLoan getOne(@PathParam(value = "userId") Integer userId, JsonLoan loan) {
    	JsonLoan jl = loan;
    	if (jl.getId() == null) {
    		Loan l = new Loan();
    		l.setUser1(userDAO.getUser(jl.getFriendId()));
    		l.setUser2(userDAO.getUser(jl.getUserId()));
    		if (jl.getAlbumId() != null) {
    			l.setAlbumBean(albumDAO.getAlbum(jl.getAlbumId()));
    		} else if (jl.getBookId() != null) {
    			l.setBookBean(bookDAO.getBook(jl.getBookId()));
    		} else if (jl.getMovieId() != null) {
    			l.setMovieBean(movieDAO.getMovie(jl.getMovieId()));
    		} else if (jl.getTvshowId() != null) {
    			l.setTvshowBean(tvshowDAO.getTvshow(jl.getTvshowId()));
    		}
    		l.setStartdate(jl.getStartDate());
    		loanDAO.saveLoan(l);
    		jl.setId(l.getId());
    	} else {
    		Loan l = loanDAO.getLoan(jl.getId());
    		if (jl.getAlbumId() != null) {
    			l.setAlbumBean(albumDAO.getAlbum(jl.getAlbumId()));
    		} else if (jl.getBookId() != null) {
    			l.setBookBean(bookDAO.getBook(jl.getBookId()));
    		} else if (jl.getMovieId() != null) {
    			l.setMovieBean(movieDAO.getMovie(jl.getMovieId()));
    		} else if (jl.getTvshowId() != null) {
    			l.setTvshowBean(tvshowDAO.getTvshow(jl.getTvshowId()));
    		}
    		l.setStartdate(jl.getStartDate());
    		l.setEnddate(jl.getEndDate());
    		loanDAO.updateLoan(l);
    	}
    	return jl;
    }

    /**
     * Get list of JsonLoan from a list of Loans
     * 
     * @param userId
     * @param ll
     * @return
     */
	private List<JsonLoan> getJsonLoansFromLoans(Integer userId, List<Loan> ll) {
		List<JsonLoan> jl = new ArrayList<>();
    	for (Loan l:ll) {
    		JsonLoan j = new JsonLoan();
    		j.setId(l.getId());
    		j.setUserId(userId);
    		j.setUserLogin(l.getUser2().getLogin());
    		j.setFriendId(l.getUser1().getId());
    		j.setFriendLogin(l.getUser1().getLogin());
    		j.setStartDate(l.getStartdate());
    		j.setEndDate(l.getEnddate());
    		if (l.getAlbumBean() != null) {
    			j.setAlbum(l.getAlbumBean().getTitle());
    			j.setAlbumId(l.getAlbumBean().getId());
    		} else if (l.getBookBean() != null) {
    			j.setBook(l.getBookBean().getTitle());
    			j.setBookId(l.getBookBean().getId());
    		} else if (l.getMovieBean() != null) {
    			j.setMovie(l.getMovieBean().getTitle());
    			j.setMovieId(l.getMovieBean().getId());
    		} else if (l.getTvshowBean() != null) {
    			j.setTvshow(l.getTvshowBean().getTitle());
    			j.setTvshowId(l.getTvshowBean().getId());
    		}
    		jl.add(j);
    	}
		return jl;
	}
	
}
