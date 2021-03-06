package com.example.memy.DAO;


import com.example.memy.model.gif;
import java.util.ArrayList;
import java.util.List;

public class GifDao {

    private static List<gif> gifs = new ArrayList<>();

    static {
        gifs.add(new gif("android-explosion", true,1,"Daniel"));
        gifs.add(new gif("ben-and-mike", false,2,"Kuba"));
        gifs.add(new gif("book-dominos", false,3,"Adolf"));
        gifs.add(new gif("compiler-bot", true,3,"Herman"));
        gifs.add(new gif("cowboy-coder", false,3,"Josef"));
        gifs.add(new gif("infinite-andrew", false,2,"Otto"));

    }
    public List<gif> showAll() {
        return gifs;

    }
    public List<gif> isLike() {
        List<gif> result = new ArrayList<>();
        for (gif gif : gifs) {
            if (gif.getLike().equals(true))
                result.add(gif);
        }
        return result;
    }
    public List<gif> findOne(String name) {
        List<gif> result = new ArrayList<>();
        for (gif gif : gifs) {
            if (gif.getName().equals(name))
                result.add(gif);
        }
        return result;
    }
    public List<gif> findByName(String name) {
        List<gif> results = new ArrayList<>();
        for (gif gif: gifs){
            if(gif.getName().equals(name))
                results.add(gif);
        }
        return results;
    }


    public List<gif> findTopRated() {
        List<gif> gifsTopRated = new ArrayList<>();
        for(int i = 0; i < gifs.size();i++) {
            if (gifs.get(i).getCatId() == 1)
                gifsTopRated.add(gifs.get(i));
        }
        return gifsTopRated;
    }


    public List<gif> findAnimals() {
        List<gif> gifsMostPopular = new ArrayList<>();
        for(int i = 0; i < gifs.size();i++) {
            if (gifs.get(i).getCatId() == 3)
                gifsMostPopular.add(gifs.get(i));
        }
        return gifsMostPopular;
    }


    public List<gif> findNew() {
        List<gif> gifsNew = new ArrayList<>();
        for(int i = 0; i < gifs.size();i++) {
            if (gifs.get(i).getCatId() == 2)
                gifsNew.add(gifs.get(i));
        }
        return gifsNew;
    }

    public List<gif> findByNameCat(String name) {
        List<gif> results = new ArrayList<>();
        if (name.equals("Top Rated")) {
            for (int i = 0; i < gifs.size(); i++) {
                if (gifs.get(i).getCatId() == 1)
                    results.add(gifs.get(i));
            }
        }

        if(name.equals("Newest")) {
            for (int i = 0; i < gifs.size(); i++) {
                if (gifs.get(i).getCatId() == 2)
                    results.add(gifs.get(i));
            }
        }

        if(name.equals("Animals")) {
            for (int i = 0; i < gifs.size(); i++) {
                if (gifs.get(i).getCatId() == 3)
                    results.add(gifs.get(i));
            }
        }

        return results;
    }

}




