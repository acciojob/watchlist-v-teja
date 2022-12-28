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
        Movie movie = movieRepository.getMovieFromDB(movieName);
        if(movieRepository.directorSet.containsKey(directorName)){
            Director director = movieRepository.getDirectorFromDB(directorName);
            if(movieRepository.pairSet.containsKey(director)){
                List<Movie> list = movieRepository.pairSet.get(director);
                for(Movie temp: list){
                    if(temp.equals(movie)){
                        return false;
                    }
                }

                        movieRepository.addMovieDirectorToPairSet(director,movie);
                        return true;

            }else{
                List<Movie> list  = new ArrayList<>();
                list.add(movie);
                movieRepository.pairSet.put(director,list);
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

    public List<Movie> getListFromPathName(String directorName){
        Director director = movieRepository.getDirectorFromDB(directorName);
        List list = movieRepository.getListFromDB(director);
        return list;
    }

    public List<Movie> getAllMovies(){
        HashMap<String,Movie> map = movieRepository.getMovieListFromDB();
        List<Movie> list = new ArrayList<>();
        for(String str: map.keySet()){
            Movie temp = map.get(str);
            list.add(temp);
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

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectorsFromDB();
    }


}
