package com.example.fujitsumovierental;

import com.example.fujitsumovierental.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class MovieTests {

    public static Movie movie1 = new Movie(100, "John Wick", 120, List.of("action"), LocalDate.now().minusWeeks(156).toString(), List.of("director"),
            List.of("writer"), List.of("actor"), "Dog gets killed, Man is angry");
    public static Movie movie2 = new Movie(100, "John Wick", 120, List.of("action"), LocalDate.now().minusWeeks(155).toString(), List.of("director"),
            List.of("writer"), List.of("actor"), "Dog gets killed, Man is angry");
    public static Movie movie3 = new Movie(100, "John Wick", 120, List.of("action"), LocalDate.now().minusWeeks(52).toString(), List.of("director"),
            List.of("writer"), List.of("actor"), "Dog gets killed, Man is angry");
    public static Movie movie4 = new Movie(100, "John Wick", 120, List.of("action"), LocalDate.now().minusWeeks(50).toString(), List.of("director"),
            List.of("writer"), List.of("actor"), "Dog gets killed, Man is angry");

    private void TestPriceClass(Movie movie, BigDecimal priceClass) {
        Assertions.assertEquals(movie.getPriceClass(), priceClass);
    }

    private void TestMovieRentingFiveWeeks(Movie movie, BigDecimal price) {
        Assertions.assertEquals(movie.rentOut(5), price);
    }

    @Test
    void TestNewMoviePriceClass() {
        TestPriceClass(movie4, BigDecimal.valueOf(5));
    }

    @Test
    void TestRegularMoviePriceClass() {
        TestPriceClass(movie3, BigDecimal.valueOf(3.49));
    }

    @Test
    void TestOldMoviePriceClass() {
        TestPriceClass(movie1, BigDecimal.valueOf(1.99));
    }

    @Test
    void TestRentOutOldMovie() {
        TestMovieRentingFiveWeeks(movie1, BigDecimal.valueOf(9.95));
    }

    @Test
    void TestRentOutRegularMovieSwapToOld() {
        TestMovieRentingFiveWeeks(movie2, BigDecimal.valueOf(11.45));
    }

    @Test
    void TestRentOutRegularMovie() {
        TestMovieRentingFiveWeeks(movie3, BigDecimal.valueOf(17.45));
    }

    @Test
    void TestRentOutNewMovieSwapToRegular() {
        TestMovieRentingFiveWeeks(movie4, BigDecimal.valueOf(20.47));
    }
}
