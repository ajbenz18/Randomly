package edu.msu.bensmana.randomly.Snippets;

import edu.msu.bensmana.randomly.Snippets.Models.Advice;
import edu.msu.bensmana.randomly.Snippets.Models.CatFact;
import edu.msu.bensmana.randomly.Snippets.Models.DogFact;
import edu.msu.bensmana.randomly.Snippets.Models.DogFact2;
import edu.msu.bensmana.randomly.Snippets.Models.Fact;
import edu.msu.bensmana.randomly.Snippets.Models.Joke;
import edu.msu.bensmana.randomly.Snippets.Models.Kanye;
import edu.msu.bensmana.randomly.Snippets.Models.Nonsense;
import edu.msu.bensmana.randomly.Snippets.Models.Quote;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface SnippetService {
    @Headers({"Accept: application/json"})
    @GET(".")
    Call<Joke> retrieveJoke(
//        @Query("Accept") String type
    );

    @GET("/random.json")
    Call<Fact> retrieveFact(
        @Query("language") String lang
    );

    @GET("/advice")
    Call<Advice> retrieveAdvice(
    );

    @GET(".")
    Call<Kanye> retrieveKanye(
    );

    @GET("/random")
    Call<Quote> retrieveQuote(
    );

    @GET(".")
    Call<Nonsense> retrieveNonsense(
    );

    @GET(".")
    Call<CatFact> retrieveCatFact(
    );

    // this API doesn't work anymore. Not sure if temporary or permanant
    @GET("/api/v1/resources/dogs")
    Call<List<DogFact>> retrieveDogFact(
            @Query("number") int number
    );

    // replacement for original method of getting dog facts
    @GET("/api/facts")
    Call<DogFact2> retrieveDogFact2(
    );
}
