
	package edu.sjsu.cmpe.library.dto;

	import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Reviews;

	//@JsonPropertyOrder(alphabetic = false)
	public class ReviewsDto extends LinksDto {
	    private Reviews review;

	    /**
	     * @param book
	     */
	    public ReviewsDto(Reviews review) {
		super();
		this.review = review;
	    }

	    /**
	     * @return the book
	     */
	    public Reviews getReview() {
		return review;
	    }

	    /**
	     * @param book
	     *            the book to set
	     */
	    public void setReview(Reviews review) {
		this.review = review;
	    }
	}

