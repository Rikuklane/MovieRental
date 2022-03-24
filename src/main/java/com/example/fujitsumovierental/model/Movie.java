package com.example.fujitsumovierental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie{

    private long id;
    private String name;
    private int runtime;
    private List<String> categories;
    private LocalDate releaseDate;
    private List<String> director;
    private List<String> writers;
    private List<String> actors;
    private String description;

    public Movie(long id, String name, int runtime, List<String> categories, String releaseDate, List<String> director, List<String> writers, List<String> actors, String description) {
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.categories = categories;
        this.releaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_LOCAL_DATE);
        this.director = director;
        this.writers = writers;
        this.actors = actors;
        this.description = description;
    }

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<String> getCategories() {
        return categories;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<String> getDirector() {
        return director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal rentOut(int durationInWeeks) {
        BigDecimal totalPrice = new BigDecimal(0);
        int weeks = (int) ChronoUnit.WEEKS.between(releaseDate, LocalDate.now());
        for (int i = 0; i < durationInWeeks; i++) {
            totalPrice = totalPrice.add(this.getPriceClass(weeks));
            weeks += 1;
        }
        return totalPrice;
    }

    @JsonIgnore
    public BigDecimal getPriceClass() {
        int weeks = (int) ChronoUnit.WEEKS.between(releaseDate, LocalDate.now());
        return getPriceClass(weeks);
    }

    @JsonIgnore
    private BigDecimal getPriceClass(int weeksOfAge) {
        if (weeksOfAge < 52) {
            return new BigDecimal(5); // new movie
        } else if (weeksOfAge < 156) {
            return BigDecimal.valueOf(3.49); // regular movie
        }
        return BigDecimal.valueOf(1.99); // old movie
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", runtime=" + runtime +
                ", categories=" + categories +
                ", releaseDate=" + releaseDate +
                ", director=" + director +
                ", writers=" + writers +
                ", actors=" + actors +
                ", description='" + description + '\'' +
                '}';
    }
}
