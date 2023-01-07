package com.driver;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.HashMap;

@Repository
public class MovieRepository {

    public HashMap<String,Movie>  movieSet;
    public HashMap<String,Director> directorSet;

    //stores director names and a list of movie names they directed.
    public HashMap<String,List<String>> movieDirectorSet;

    MovieRepository(){
        this.movieSet = new HashMap<>();
        this.directorSet = new HashMap<>();
        this.movieDirectorSet = new HashMap<>();
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

        List<String> list = movieDirectorSet.get(directorName);
        list.add(movieName);
        movieDirectorSet.put(directorName,list);
    }

    public Movie getMovieFromDB(String movieName){
        return movieSet.get(movieName);
    }

    public Director getDirectorFromDB(String directorName){
        return directorSet.get(directorName);
    }

    public List<String> getListFromDB(String directorName){
        return movieDirectorSet.get(directorName);
    }

    public HashMap<String,Movie> getMovieListFromDB(){
        return movieSet;
    }

    public void deleteDirectorFromDB(String directorName){
        if(movieDirectorSet.containsKey(directorName)){
            List<String> list = movieDirectorSet.get(directorName);
            for(String movieName: list){
                movieSet.remove(movieName);
            }
            movieDirectorSet.remove(directorName);
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


    public String getDirectorNameByMovieName(String movieName) {
        for(String str: movieDirectorSet.keySet() ){
            for(String res: movieDirectorSet.get(str)){
                if(res.equals(movieName)){
                    return str;
                }
            }
        }

        return null;
    }
}
