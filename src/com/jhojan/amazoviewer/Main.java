package com.jhojan.amazoviewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.jhojan.amazoviewer.model.Book;
import com.jhojan.amazoviewer.model.Chapter;
import com.jhojan.amazoviewer.model.Film;
import com.jhojan.amazoviewer.model.Magazine;
import com.jhojan.amazoviewer.model.Movie;
import com.jhojan.amazoviewer.model.Serie;
import com.anncode.makereport.Report;

/**
 * <h1>AmazonViewer</h1>
 * AmazonViewer es un programa que permite visualizar Movies, Series con sus respectivo Chapters,
 * Books y Magazines. Te permite generar reportes generales y con fecha del día
 * <p>
 * Existen algunas reglas como que todos los elementos pueden ser visualizados a excepción de los Magazines, estas
 * solo pueden ser vistas a modo de exposición sin ser leidas.
 * 
 * @author Jhojan García
 * @version 1.1
 * @since 2018
 * 
 * */

public class Main {
	
	static ArrayList<Movie> movies = new ArrayList();
	static ArrayList<Book> books= Book.makeBookList();
	static ArrayList<Serie> series = Serie.makeSeriesList();

	public static void main(String[] args) {
		showMenu();
	}
	
	
	
	public static void showMenu() {
			int exit = 1;
			do {
				System.out.println("Bienvenidos a Amazon Viewer\n");
				System.out.println("Selecciona el numero de la opción deseada \n");
				System.out.println("1. Peliculas\n");
				System.out.println("2. Series\n");
				System.out.println("3. Libros\n");
				System.out.println("4. Revistas\n");
				System.out.println("5. Reporte\n");
				System.out.println("6. Reporte hoy\n");
				
				Scanner sc = new Scanner(System.in);
				int sw = sc.nextInt();
				
				switch (sw) {
				case 0:
					exit = 0;
					break;	
				case 1:
					showMovies();
					break;
				case 2:
					showSeries();
					break;
				case 3:
					showBooks();
					break;
				case 4:
					showMagazines();
					break;
				case 5:
					makeReport();
					break;
				case 6:
					makeReport(new Date());
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
			}while(exit != 0);
		}
	public static void showMovies() {
		movies = Movie.makeMoviesList();
		int exit = 1;
		do {
			System.out.println();
			System.out.println(":: MOVIES ::");
			System.out.println();
			
			AtomicInteger atomicInteger = new AtomicInteger(12);
			movies.forEach(m->System.out.println(atomicInteger.getAndIncrement() + ". " + m.getTitle() + " Visto: " + m.isViewed()));
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			Scanner sc = new Scanner(System.in);
			int response = Integer.valueOf(sc.nextLine());
			
			if(response == 0) {
				showMenu();
			}
			if(response > 0) {
				Movie movieSelected = movies.get(response-1);
				movieSelected.view();
			}			
		}while(exit !=0);
	}
	public static void showSeries() {
		int exit = 1;
		do {
			System.out.println();
			System.out.println(":: SERIES ::");
			System.out.println();
			
			for (int i = 0; i < series.size(); i++) { //1. Serie 1
				System.out.println(i+1 + ". " + series.get(i).getTitle() + " Visto: " + series.get(i).isViewed());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			Scanner sc = new Scanner(System.in);
			int response = Integer.valueOf(sc.nextLine());
			
			if(response == 0) {
				showMenu();
			}
			
			if(response > 0) {
				showChapters(series.get(response-1).getChapters());
			}			
		}while(exit !=0);
	}
	
	public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
		int exit = 1;
		do {
			System.out.println();
			System.out.println(":: CHAPTERS ::");
			System.out.println();
			
			for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
				System.out.println(i+1 + ". " + chaptersOfSerieSelected.get(i).getTitle() + " Visto: " + chaptersOfSerieSelected.get(i).isViewed());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			Scanner sc = new Scanner(System.in);
			int response = Integer.valueOf(sc.nextLine());
			
			if(response == 0) {
				showSeries();
			}
			
			if(response > 0) {
				Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
				chapterSelected.view();
			}			
				
		}while(exit !=0);
	}
	public static void showBooks() {
		int exit = 1;
		do {
			System.out.println();
			System.out.println(":: BOOKS ::");
			System.out.println();
			
			for (int i = 0; i < books.size(); i++) { //1. Book 1
				System.out.println(i+1 + ". " + books.get(i).getTitle() + " Leido: " + books.get(i).isRead());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			Scanner sc = new Scanner(System.in);
			int response = Integer.valueOf(sc.nextLine());
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			if(response > 0) {
				Book bookSelected = books.get(response-1);
				bookSelected.view();
			}
			
		}while(exit !=0);
	}
	public static void showMagazines() {
		ArrayList<Magazine> magazines = Magazine.makeMagazineList();
		int exit = 0;
		do {
			System.out.println();
			System.out.println(":: MAGAZINES ::");
			System.out.println();
			
			for (int i = 0; i < magazines.size(); i++) { //1. Book 1
				System.out.println(i+1 + ". " + magazines.get(i).getTitle());
			}
			
			System.out.println("0. Regresar al Menu");
			System.out.println();
			
			//Leer Respuesta usuario
			Scanner sc = new Scanner(System.in);
			int response = Integer.valueOf(sc.nextLine());
			
			if(response == 0) {
				exit = 0;
				showMenu();
			}
			
			
		}while(exit !=0);
	}
	
	public static void makeReport() {
		Report report = new Report();
		report.setNameFile("Reporte");
		report.setExtension("txt");
		report.setTitle("::VISTOS::");
		StringBuilder contentReport = new StringBuilder();
		
		movies.stream().filter(m -> m.getIsViewed()).forEach(m->contentReport.append(m.toString() + "\n"));
		//Predicate<Serie> viewedSeries = s -> s.getIsViewed();
		//Consumer<Serie> seriesEach = m -> contentReport.append(m.toString() + "\n");
		Consumer<Serie> seriesEach = s -> {
			ArrayList<Chapter> chapters = s.getChapters();
			chapters.stream().filter(c -> c.getIsViewed()).forEach(c -> contentReport.append(c.toString() + "\n"));;
		};
		series.stream().forEach(seriesEach);
		
		//books.stream().filter(b -> b.isRead()).forEach(b->contentReport.append(b.toString() + "\n"));
		report.setContent(contentReport.toString());
		report.makeReport();
	}
	
	public static void makeReport(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = df.format(date);
		Report report = new Report();
		report.setNameFile("Reporte: "+dateString);
		report.setExtension("txt");
		report.setTitle("::VISTOS::");
		String contentReport = "";
		
		for (Movie movie : movies) {
			if(movie.getIsViewed()) {
				contentReport += movie.toString() + "\n";
			}
		}
		
		report.setContent(contentReport);
		report.makeReport();
	}
}
