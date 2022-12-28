package com.driver;

public class Director {
    //String name, int numberOfMovies, double imdbRating,
    // no-args constructor, all-args constructor and getters-setters

    private String name;
    private int numberOfMovies;
    private double imdbRating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    //constructor
    Director(){
    this.name = null;
    this.numberOfMovies = 0;
    this.imdbRating = 0.0;
    }

    //constructor all args

    public Director(String name, int numberOfMovies, double imdbRating) {
        this.name = name;
        this.numberOfMovies = numberOfMovies;
        this.imdbRating = imdbRating;
    }
}
