package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("movieRecommender")
public class MovieRecommender {
    @Autowired
    private MovieCatelog catelog;

    public MovieCatelog getCatelog() {
        return catelog;
    }

    public void setCatelog(MovieCatelog catelog) {
        this.catelog = catelog;
    }
}
