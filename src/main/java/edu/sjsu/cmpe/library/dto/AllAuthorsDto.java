package edu.sjsu.cmpe.library.dto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Authors;

import java.util.ArrayList;

public class AllAuthorsDto extends LinksDto {

    private ArrayList<Authors> allauthors = new ArrayList<Authors>();

    public AllAuthorsDto(ArrayList<Authors> allauthors) 
    {
	super();
	this.allauthors = allauthors;
    }

    /**
     * @return the book
     */
    public ArrayList<Authors> getAuthors() {
	return allauthors;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setAuthors(ArrayList<Authors> allauthors) {
	this.allauthors = allauthors;
    } 
}
