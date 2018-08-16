package com.jhojan.amazoviewer.model;

import java.util.ArrayList;
import java.util.Date;

public class Movie extends Film implements IVisualizable{
	private int id;
	private int timeViewd;
	
	
	
	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}

	public void showData() {
		//System.out.println("Title: "+ this.title);
		//System.out.println("Genre: "+this.genre);
		//System.out.println("Year: "+this.year);
	}

	public int getId() {
		return id;
	}
	
	public int getTimeViewd() {
		return timeViewd;
	}

	public void setTimeViewd(int timeViewd) {
		this.timeViewd = timeViewd;
	}	
	
	@Override
	public String toString() {
		return "\n Title: "+getTitle()+
				"\n Genero: "+getGenre()+
				"\n Year: "+getYear()+
				"\n Creator: "+getCreator()+
				"\n Duration: "+getDuration();
	}

	@Override
	public Date startToSee(Date dateI) {
		// TODO Auto-generated method stub
		return dateI;
	}

	@Override
	public void stopToSee(Date dateI, Date dateF) {
		// TODO Auto-generated method stub
		int result = dateF.getTime() > dateI.getTime() ? (int) (dateF.getTime() - dateI.getTime()) / 1000 : 0;
		this.setTimeViewd(result);
		
	}
	
	public static ArrayList<Movie> makeMoviesList() {
		ArrayList<Movie> movies = new ArrayList();
		
		for (int i = 1; i <= 5; i++) {
			movies.add(new Movie("Movie " + i, "Genero " + i, "Creador " + i, 120+i, (short)(2017+i)));
		}
		
		return movies;
	}

	@Override
	public void view() {
		// TODO Auto-generated method stub
		
	}
}
