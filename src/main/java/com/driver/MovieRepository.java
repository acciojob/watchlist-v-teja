package com.driver;
import org.springframework.remoting.support.DefaultRemoteInvocationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.HashMap;

@Repository
public class MovieRepository {

    public HashMap<String,Movie>  movieSet;
    public HashMap<String,Director> directorSet;
    public HashMap<Director,List<Movie>> pairSet;

    MovieRepository(){
        this.movieSet = new HashMap<>();
        this.directorSet = new HashMap<>();
        this.pairSet = new HashMap<>();
    }

    public void addMovieToDB(Movie movie){
        String key = movie.getName();
        movieSet.put(key,movie);
    }

    public void addDirectorToDB(Director director){
        String key = director.getName();
        directorSet.put(key,director);
    }

    public void addMovieDirectorToPairSet(Director director, Movie movie){

        List<Movie> list = pairSet.get(director);
        list.add(movie);
        pairSet.put(director,list);
    }

    public Movie getMovieFromDB(String movieName){
        return movieSet.get(movieName);
    }

    public Director getDirectorFromDB(String directorName){
        return directorSet.get(directorName);
    }

    public List<Movie> getListFromDB(Director director){
        return pairSet.get(director);
    }

    public HashMap<String,Movie> getMovieListFromDB(){
        return movieSet;
    }

    public void deleteDirectorFromDB(String directorName){
        Director director = directorSet.get(directorName);
        directorSet.remove(directorName);
        if(pairSet.containsKey(director)){
            for(Movie movie: pairSet.get(director)){
                movieSet.remove(movie.getName());
            }
            pairSet.remove(director);
        }
    }

   public void deleteAllDirectorsFromDB(){
        for(String str: directorSet.keySet()){
            deleteDirectorFromDB(str);
        }
   }


}
