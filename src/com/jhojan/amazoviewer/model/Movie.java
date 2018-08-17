package com.jhojan.amazoviewer.model;

import java.util.ArrayList;
import java.util.Date;

import com.jhojan.amazonviewer.dao.MovieDAO;

/**
 * Hereda de {@link Film}
 * Implementa de {@link IVisualizable}*/
public class Movie extends Film implements IVisualizable, MovieDAO{
	private int id;
	private int timeViewd;
	
	public Movie() {}
	
	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}

	public void showData() {
		//System.out.println("Title: "+ this.title);
		//System.out.println("Genre: "+this.genre);
		//System.out.println("Year: "+this.year);
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	/**
	 * {@inheritDoc}}
	 * */
	@Override
	public Date startToSee(Date dateI) {
		// TODO Auto-generated method stub
		return dateI;
	}
	
	/**
	 * {@inheritDoc}}
	 * */
	@Override
	public void stopToSee(Date dateI, Date dateF) {
		// TODO Auto-generated method stub
		int result = dateF.getTime() > dateI.getTime() ? (int) (dateF.getTime() - dateI.getTime()) / 1000 : 0;
		this.setTimeViewd(result);
		
	}
	
	public static ArrayList<Movie> makeMoviesList() {
		Movie movie = new Movie();
		
		return movie.read();
	}
	
	/**
	 * {@inheritDoc}}
	 * */
	@Override
	public void view() {
		setViewed(true);
		Movie movie = new Movie();
		movie.setMovieViewed(this);
		Date dateI = startToSee(new Date());
		
		for (int i = 0; i < 100000; i++) {
			System.out.println("..........");
		}
		
		//Termine de verla
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Viste: " + toString());
		System.out.println("Por: " + getTimeViewd() + " milisegundos");
		
	}
}
