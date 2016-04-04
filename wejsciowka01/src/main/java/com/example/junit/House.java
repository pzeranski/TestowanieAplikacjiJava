package com.example.junit;

public class House {

	private String ulica;

	private boolean dostepny;

	private int numer;

	public House(){
	}

	public House(String ulica, boolean dostepny, int numer){
		this.ulica = ulica;
		this.dostepny = dostepny;
		this.numer = numer;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public boolean getDostepny() {
		return dostepny;
	}

	public void setDostepny(boolean dostepny) {
		this.dostepny = dostepny;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}



}
