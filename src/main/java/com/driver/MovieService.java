package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public boolean addMovieByBody(Movie movie){
        if(movieRepository.movieSet.containsKey(movie.getName())){
            return false;
        }

        movieRepository.addMovieToDB(movie);
        return true;
    }

    public boolean addDirectorByBody(Director director){
        if(movieRepository.directorSet.containsKey(director.getName())){
            return false;
        }else
            movieRepository.addDirectorToDB(director);
        return true;
    }

    public boolean addMovieDirectorByNames(String movieName, String directorName){

        if(!movieRepository.movieSet.containsKey(movieName)){
            return false;
        }
        if(movieRepository.directorSet.containsKey(directorName)){
            if(movieRepository.movieDirectorSet.containsKey(directorName)){
                List<String> list = movieRepository.movieDirectorSet.get(directorName);
                for(String temp: list){
                    if(temp.equals(movieName)){
                        return false;
                    }
                }

                        movieRepository.addMovieDirectorToPairSet(directorName,movieName);
                        return true;

            }else{
                List<String> list  = new ArrayList<>();
                list.add(movieName);
                movieRepository.movieDirectorSet.put(directorName,list);
                return true;
            }
        }

        return false;
    }

    public Movie getMovieFromPathName(String movieName){
        if(movieRepository.movieSet.containsKey(movieName)){
            return movieRepository.getMovieFromDB(movieName);
        }

        return null;
    }

    public Director getDirectorFromPathName(String directorName){
        if(movieRepository.directorSet.containsKey(directorName)){
            return movieRepository.getDirectorFromDB(directorName);
        }

        return null;
    }

    public List<String> getListFromPathName(String directorName){

        List list = movieRepository.getListFromDB(directorName);
        return list;
    }

    public List<String> getAllMovies(){
        HashMap<String,Movie> map = movieRepository.getMovieListFromDB();
        List<String> list = new ArrayList<>();
        for(String str: map.keySet()){
            list.add(str);
        }

        return list;
    }

    public boolean deleteDirector(String directorName){
        if(movieRepository.directorSet.containsKey(directorName)){
            movieRepository.deleteDirectorFromDB(directorName);
            return true;
        }
        return false;
    }

    public boolean deleteAllDirectors(){
        return movieRepository.deleteAllDirectorsFromDB();
    }


    public String getDirectorNameByMovieName(String movieName) {
        return movieRepository.getDirectorNameByMovieName(movieName);
    }
}
