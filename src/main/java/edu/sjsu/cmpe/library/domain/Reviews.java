package edu.sjsu.cmpe.library.domain;

public class Reviews 
{
	 private int id;
	 private int rating;
	 private String comment;
	 
	 
	    public int getId() 
	    {
		return id;
	    }

	    /**
	     * @param rating
	     *            the rating to set for the book
	     */
	    public void setId(int id) 
	    {
		 this.id = id;
	    }
	 
	 //1
	    /**
	     * @return the rating for the book
	     */
	    public int getRating() 
	    {
		return rating;
	    }

	    /**
	     * @param rating
	     *            the rating to set for the book
	     */
	    public void setRating(int rating) 
	    {
		 this.rating = rating;
	    }

	//2
	    public String getComment() 
	    {
		return comment;
	    }

	    /**
	     * @param rating
	     *            the rating to set for the book
	     */
	    public void setComment(String comment) 
	    {
		this.comment = comment;
	    }

	    
}
