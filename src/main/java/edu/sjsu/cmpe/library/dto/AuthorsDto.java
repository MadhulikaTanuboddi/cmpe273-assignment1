
	package edu.sjsu.cmpe.library.dto;

	import com.fasterxml.jackson.annotation.JsonPropertyOrder;

	import edu.sjsu.cmpe.library.domain.Authors;
	import java.util.ArrayList;


	@JsonPropertyOrder(alphabetic = true)
	public class AuthorsDto extends LinksDto {
	    private Authors author;
	    
	    /**
	     * @param book
	     */
	    public AuthorsDto(Authors author) 
	    {
		super();
		this.author = author;
	    }


	    
	    /**
	     * @return the book
	     */
	    public Authors getAuthor() {
		return author;
	    }

	    /**
	     * @param book
	     *            the book to set
	     */
	    public void setAuthor(Authors author) {
		this.author = author;
	    }
	    

	    
	}

