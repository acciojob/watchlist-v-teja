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
    public HashMap<String,List<String>> pairSet;

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

    public void addMovieDirectorToPairSet(String directorName, String movieName){

        List<String> list = pairSet.get(directorName);
        list.add(movieName);
        pairSet.put(directorName,list);
    }

    public Movie getMovieFromDB(String movieName){
        return movieSet.get(movieName);
    }

    public Director getDirectorFromDB(String directorName){
        return directorSet.get(directorName);
    }

    public List<String> getListFromDB(String directorName){
        return pairSet.get(directorName);
    }

    public HashMap<String,Movie> getMovieListFromDB(){
        return movieSet;
    }

    public void deleteDirectorFromDB(String directorName){
        if(pairSet.containsKey(directorName)){
            List<String> list = pairSet.get(directorName);
            for(String movieName: list){
                movieSet.remove(movieName);
            }
            pairSet.remove(directorName);
        }
        directorSet.remove((directorName));
    }

   public boolean deleteAllDirectorsFromDB(){
        if(directorSet.isEmpty()){
            return false;
        }

        for(String str: directorSet.keySet()){
            deleteDirectorFromDB(str);
        }
        return true;
   }


}
