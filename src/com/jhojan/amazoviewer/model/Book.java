package com.jhojan.amazoviewer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Book extends Publication implements IVisualizable {
	private int id;
	private String isbn;
	private boolean read;
	private int timeRead;
	private ArrayList<Page> pages;

	public Book(String title, Date edititionDate, String editorial, String[] authors, ArrayList<Page> pages) {
		super(title, edititionDate, editorial);
		// TODO Auto-generated constructor stub
		setAuthors(authors);
		this.pages = pages;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public int getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String isRead() {
		return read ? "Si" : "no";
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public int getTimeRead() {
		return timeRead;
	}

	public void setTimeRead(int timeRead) {
		this.timeRead = timeRead;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String detailBook = "\n :: BOOK ::" + 
							"\n Title: " + getTitle() +
							"\n Editorial: " + getEditorial() + 
							"\n Edition Date: " + getEditorialDate() +
							"\n Authors: ";
		for (int i = 0; i < getAuthors().length; i++) {
			detailBook += "\t" + getAuthors()[i];
		}
		return  detailBook;
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
		this.setTimeRead(result);
		
	}
	
	public void view() {
		setRead(true);
		Date dateI = startToSee(new Date());
		
		int i = 0;
		do {
			System.out.println(".............");
			System.out.println("Page "+getPages().get(i).getNumber());
			System.out.println(getPages().get(i).getContent());
			System.out.println(".............");
			
			if(i != 0) { 
				System.out.println("1. Regresar página");
			}
			System.out.println("2. Siguiente página");
			System.out.println("0. Cerrar libro");
			System.out.println();
			Scanner sc = new Scanner(System.in);
			int response = Integer.valueOf(sc.nextLine());
			if(response == 2) {
				i++;
			}else if(response == 1) {
				i--;
			}else if(response == 0) {
				break;
			}
		}while(i < getPages().size());
		
		//Termine de verla
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Leiste: " + toString());
		System.out.println("Por: " + getTimeRead() + " milisegundos");
	}
	
	public static ArrayList<Book> makeBookList() {
		ArrayList<Book> books = new ArrayList();
		String[] authors = new String[3];
		for (int i = 0; i < 3; i++) {
			authors[i] = "author "+i;
		}
		
		ArrayList<Page> pages = new ArrayList();
		int pagina = 0;
		for (int i = 0; i < 3; i++) {
			pagina = i+1;
			pages.add(new Book.Page(pagina, "El contenido de la página: " + pagina));
		}
		
		for (int i = 1; i <= 5; i++) {
			books.add(new Book("Book " + i, new Date(), "editorial " + i, authors, pages));
		}
		
		return books;
	}
	
	public static class Page {
		private int id;
		private int number;
		private String content;
		
		public Page(int number, String content) {
			super();
			this.number = number;
			this.content = content;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
}
