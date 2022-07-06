package edu.msu.bensmana.randomly.Snippets;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import edu.msu.bensmana.randomly.Snippets.Models.Advice;
import edu.msu.bensmana.randomly.Snippets.Models.CatFact;
import edu.msu.bensmana.randomly.Snippets.Models.DogFact;
import edu.msu.bensmana.randomly.Snippets.Models.DogFact2;
import edu.msu.bensmana.randomly.Snippets.Models.Fact;
import edu.msu.bensmana.randomly.Snippets.Models.Joke;
import edu.msu.bensmana.randomly.Snippets.Models.Kanye;
import edu.msu.bensmana.randomly.Snippets.Models.Nonsense;
import edu.msu.bensmana.randomly.Snippets.Models.Quote;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentRetriever {

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public static final String JOKE_PATH = "https://icanhazdadjoke.com";

    public static final String FACT_PATH = "https://uselessfacts.jsph.pl";

    public static final String ADVICE_PATH = "https://api.adviceslip.com";

    public static final String KANYE_PATH = "https://api.kanye.rest/";

    public static final String QUOTE_PATH = "https://api.quotable.io";

    public static final String NONSENSE_PATH = "https://corporatebs-generator.sameerkumar.website/";

    public static final String CAT_PATH = "https://meowfacts.herokuapp.com/";

    public static final String DOG_PATH = "https://dog-facts-api.herokuapp.com/";

    public static final String DOG_PATH2 = "https://dog-api.kinduff.com/";




    private static Retrofit retrofitJoke = new Retrofit.Builder().baseUrl(JOKE_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitFact = new Retrofit.Builder().baseUrl(FACT_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitAdvice = new Retrofit.Builder().baseUrl(ADVICE_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitKanye = new Retrofit.Builder().baseUrl(KANYE_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitQuote = new Retrofit.Builder().baseUrl(QUOTE_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitNonsense = new Retrofit.Builder().baseUrl(NONSENSE_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitCat = new Retrofit.Builder().baseUrl(CAT_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitDog = new Retrofit.Builder().baseUrl(DOG_PATH).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static Retrofit retrofitDog2 = new Retrofit.Builder().baseUrl(DOG_PATH2).addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public String getSnippet(String type){
        if (type.equals("joke")){
            return getJoke();
        }
        else if (type.equals("fact")){
            return getFact();
        }
        else if (type.equals("advice")){
            return getAdvice();
        }
        else if (type.equals("kanye")){
            return getKanye();
        }
        else if (type.equals("quote")){
            return getQuote();
        }
        else if (type.equals("nonsense")){
            return getNonsense();
        }
        else if (type.equals("cat")){
            return getCatFact();
        }
        else if (type.equals("dog")){
            return getDogFact();
        }
        else if (type.equals("dog2")){
            return getDogFact2();
        }
        return null;
    }

    public String getJoke(){
        SnippetService service = retrofitJoke.create(SnippetService.class);
        try{
            Response<Joke> response = service.retrieveJoke().execute();

            Joke joke = response.body();
            String j = joke.getJoke();
            return j;
        }
        catch (IOException | RuntimeException e){
            Log.e("getJoke", "Exception occurred while getting joke");
            return null;
        }
    }

    public String getFact(){
        SnippetService service = retrofitFact.create(SnippetService.class);
        try{
            Response<Fact> response = service.retrieveFact("en").execute();

            Fact fact = response.body();
            String j = fact.getText();
            return j;
        }
        catch (IOException | RuntimeException e){
            Log.e("getFact", "Exception occurred while getting fact");
            return null;
        }
    }

    public String getAdvice(){
        SnippetService service = retrofitAdvice.create(SnippetService.class);
        try{
            Response<Advice> response = service.retrieveAdvice().execute();

            Advice advice = response.body();
            String a = advice.getSlip().getAdvice();
            return a;
        }
        catch (IOException | RuntimeException e){
            Log.e("getAdvice", "Exception occurred while getting advice");
            return null;
        }
    }

    public String getKanye(){
        SnippetService service = retrofitKanye.create(SnippetService.class);
        try{
            Response<Kanye> response = service.retrieveKanye().execute();

            Kanye kanye = response.body();
            String k = "\"" + kanye.getQuote()  + "\"";
            return k;
        }
        catch (IOException | RuntimeException e){
            Log.e("getKanye", "Exception occurred while getting Kanye West quote");
            return null;
        }
    }

    public String getQuote(){
        SnippetService service = retrofitQuote.create(SnippetService.class);
        try{
            Response<Quote> response = service.retrieveQuote().execute();

            Quote quote = response.body();
            String q = "\"" + quote.getContent() + "\"" + "\n- " + quote.getAuthor();
            return q;
        }
        catch (IOException | RuntimeException e){
            Log.e("getQuote", "Exception occurred while getting famous quote");
            return null;
        }
    }

    public String getNonsense(){
        SnippetService service = retrofitNonsense.create(SnippetService.class);
        try{
            Response<Nonsense> response = service.retrieveNonsense().execute();

            Nonsense nonsense = response.body();
            String n = nonsense.getPhrase();
            return n;
        }
        catch (IOException | RuntimeException e){
            Log.e("getNonsense", "Exception occurred while getting corporate nonsense");
            return null;
        }
    }

    public String getCatFact(){
        SnippetService service = retrofitCat.create(SnippetService.class);
        try{
            Response<CatFact> response = service.retrieveCatFact().execute();

            CatFact cat = response.body();
            String c = cat.getData()[0];
            return c;
        }
        catch (IOException | RuntimeException e){
            Log.e("getCatFact", "Exception occurred while getting cat fact");
            return null;
        }
    }

    public String getDogFact(){
        SnippetService service = retrofitDog.create(SnippetService.class);
        try{
            Response<List<DogFact>> response = service.retrieveDogFact(1).execute();

            DogFact dog = response.body().get(0);
            String d = dog.getFact();
            return d;
        }
        catch (IOException | RuntimeException e){
            Log.e("getDogFact", "Exception occurred while getting dog fact");
            return null;
        }
    }

    public String getDogFact2(){
        SnippetService service = retrofitDog2.create(SnippetService.class);
        try{
            Response<DogFact2> response = service.retrieveDogFact2().execute();

            DogFact2 dog = response.body();
            String d = dog.getFacts()[0];
            return d;
        }
        catch (IOException | RuntimeException e){
            Log.e("getDogFact2", "Exception occurred while getting dog fact of type 2");
            return null;
        }
    }
}
