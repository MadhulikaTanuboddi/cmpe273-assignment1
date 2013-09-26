package edu.sjsu.cmpe.library.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.AllAuthorsDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.dto.AllReviewsDto;


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }
    
    //1: ROOT API
    
    
   //2: CREATE BOOK API
    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) 
    {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);
	String location = "/books/" + savedBook.getIsbn();

	LinksDto bookResponse = new LinksDto();
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "POST"));
	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", location + "/reviews", "POST"));
	
	return Response.status(201).entity(bookResponse).build();
    }
    
    //3. VIEW BOOK API
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) 
    {
	Book book = bookRepository.getBookByISBN(isbn.get());
	
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn() + "/reviews", "POST"));
	
	if(book.getReviews().size()>0)
		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn() + "/reviews", "GET")); //if reviews>0

	return Response.status(200).entity(bookResponse).build();

    }
    
    
    //4: DELETE BOOK API
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBook(@PathParam("isbn") LongParam isbn) 
    {
        bookRepository.removeBook(isbn.get());
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("create-book", "/books/", "POST"));
    	return Response.status(200).entity(bookResponse).build();
    	
    	//ADD a condition/exception when an non-existent book is deleted
    }
    
    //5: UPDATE BOOK API
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String newstatus)  
    {
    	/*For "Status" property in book class, 
    	 * if value entered in request payload is not matching 
    	 * with the values given in assignment 
    	 * (available, lost, checked out etc) then  we need to throw error
    	 */
    	
  
        bookRepository.ModifyBookStatus(isbn.get(), newstatus);
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
    	bookResponse.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(), "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn() + "/reviews", "POST"));
    	if(book.getReviews().size()>0)
    		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn() + "/reviews", "GET")); //if reviews>0

    	return Response.status(200).entity(bookResponse).build();
    }
    
    //6: CREATE BOOK REVIEW API
    @POST
    @Path("/{isbn}/reviews")   
    @Timed(name = "create-review")
    public Response createBookReview(@PathParam("isbn") LongParam isbn, Reviews request) 
    {
	
    	Book book = bookRepository.getBookByISBN(isbn.get());	
    	
    	//add review
    	book.getReviews().add(request);
    	
    	//generate Id for the review
    	int size = book.getReviews().size();
    	book.getReviews().get(size-1).setId(size);
   
    	String location = "/books/" + book.getIsbn();
    	LinksDto bookResponse = new LinksDto();
    	bookResponse.addLink(new LinkDto("view-review" + location + "/reviews/" + size,   "GET"));
	
    	return Response.status(201).entity(bookResponse).build();
    }
    
     
    
    //7: VIEW BOOK REVIEW API
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-review")
    public Response viewReviewbyId(@PathParam("isbn") LongParam isbn, @PathParam("id")  int id) 
    {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	Reviews review = book.getReviews().get(id-1);

    	ReviewsDto reviewResponse = new ReviewsDto(book.getReviews().get(id-1));
    	reviewResponse.addLink(new LinkDto("view-book", "/books/" + isbn + "/reviews/"+ id,"GET"));
    	
    	//return reviewResponse;
    	return Response.status(200).entity(reviewResponse).build();

    }
    
    
    //8:VIEW ALL REVIEWS API
    @GET
    @Path("/{isbn}/reviews/")
    @Timed(name = "view-all-reviews")
    public Response viewAllReviews(@PathParam("isbn") LongParam isbn) 
    {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	int rsize= book.getReviews().size();
	
    	/*if (rsize>0) 
    	{
    	    for(int id = 0; id<rsize; id++)
    		{
			Reviews review = book.getReviews().get(id);
    		}
    	}*/
    	//return book.getReviews();
    	AllReviewsDto reviewResponse = new AllReviewsDto(book.getReviews());
    	
    	return Response.status(200).entity(reviewResponse).build();
    }
    
    
    
    //9: VIEW BOOK AUTHOR API 
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
    public Response viewParticularAuthor(@PathParam("isbn") LongParam isbn, @PathParam("id")  int id) 
    {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	
    	AuthorsDto authorResponse = new AuthorsDto(book.getAuthors().get(id-1));
    	authorResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn() + "/authors/"+id,"GET"));
    	
    	//return authorResponse;
    	return Response.status(200).entity(authorResponse).build();

    }
    
    
    //10: VIEW ALL AUTHORS OF THE BOOK API
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-author")
    public  Response viewBookAuthors(@PathParam("isbn") LongParam isbn) 
    {
	Book book = bookRepository.getBookByISBN(isbn.get());
	
	AllAuthorsDto authorResponse = new AllAuthorsDto(book.getAuthors());
	
	return Response.status(200).entity(authorResponse).build();

    }
    
       
}

