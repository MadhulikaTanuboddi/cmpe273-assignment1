package edu.sjsu.cmpe.library.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"isbn", "title", "publication_date", "language", "num_pages", "status", "reviews", "authors"}) 
public class Book 
{
    private long isbn;
    private String title;
    @JsonProperty("publication-date")
		private String publication_date;
    private String language;
    @JsonProperty("num-pages")
		private int num_pages;  
    private String status;
    @JsonProperty("authors")
    	private ArrayList<Authors> authors = new ArrayList<Authors>();
    @JsonProperty("reviews")
    	private ArrayList<Reviews> reviews = new ArrayList<Reviews>();
    
    
 
    // add more fields here
    //1
    /**
     * @return the isbn
     */
    public long getIsbn() 
    {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) 
    {
	this.isbn = isbn;
    }
    
    
    //2
    /**
     * @return the title
     */
    public String getTitle() 
    {	
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) 
    {
	this.title = title;
    }
    
    
    
    //3
    
    /**
     * @return the language
     */
    public String getLanguage() 
    {
	return language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language)
    {
	this.language = language;
    }
    
    //4
    
    /**
     * @return status
     */
    public String getStatus() 
    {
	return status;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setStatus(String status) 
    {
	this.status = status;
    }
    
    //5
    /**
     * @return pub date
     */
    public String getpublication_date() 
    {
	return publication_date;
    }

    /**
     * @param pub date
     */
    public void setpublication_date(String publication_date) 
    {
	this.publication_date = publication_date;
    }  
  
    //6
    /**
     * @return numpages
     */
    public int getnum_pages()
    {
	return num_pages;
    }

    /**
     * @param numpages
     */
    public void setnum_pages(int num_pages)
    {
	this.num_pages = num_pages;
    } 
    
    //7
    /**
     * @return authors
     */
    /*
    public void setAuthors(ArrayList<String> authors)
    {
    	this.authors.add(1, authors.get(1) );
    	//authors.add(2, authors.get(2) );
    }
    
    public String getAuthors()
    {
    	return authors.get(1);
    	
    }
    */
    
    //AUTHORS : get, set
    public ArrayList<Authors> getAuthors()
    {
	return authors;
    }

    /**
     * @param numpages
     */
    public void setAuthors(ArrayList<Authors> authors)
    {
	this.authors = authors;
    } 
    
    //REVIEWS: get, set  
    public ArrayList<Reviews> getReviews()
    {
    	return reviews;
    }
    
    public void setReviews(ArrayList<Reviews> reviews)
    {
    	this.reviews = reviews;
    }   
    
}
