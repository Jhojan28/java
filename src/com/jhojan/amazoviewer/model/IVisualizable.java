package com.jhojan.amazoviewer.model;

import java.util.Date;

public interface IVisualizable {
	/**
	 * Este método captua el tiempo exacto de inicio de visualización
	 * @param dateI es un objeto {@code Date} con el tiempo de inicio exacto
	 * @return Devuelve la fecha y hora caputara
	 * */
	Date startToSee(Date dateI);
	/**
	 * Este metodo capura el tiempo exacto de inicio y final de visualización
	 @param dateI es un objeto {@code Date} con el tiempo de inicio exacto
	 @param dateF es un objeto {@code Date} con el tiempo de final exacto
	 */
	void stopToSee(Date dateI, Date dateF);
}
