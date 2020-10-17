package com.example.pocketnews;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Spiderbot implements Runnable{
    private static final int MAX_DEPTH = 4;
    private static final int MAX_ARTICLE = 10;
    private Thread thread;
    private String source;
    private String first_link;
    private int count;
    private ArrayList<String> visitedLinks = new ArrayList<>();

    //constructor
    public Spiderbot(String source, String link){
        this.source = source;
        first_link = link;
        count = 0;

        //create a thread for this spider bot and run it
        thread = new Thread(this);
    }

    //******** Public Method ********
    @Override
    public void run() {
//        String[] links = classify();
//        for(int i = 0; i < links.length; i++){
//            switch (i){
//                case 0:
//                    crawl(2, links[i], "politic");
//                    break;
//                case 1:
//                    crawl(2, links[i], "technology");
//                    break;
//                case 2:
//                    crawl(2, links[i], "world");
//                    break;
//                case 3:
//                    crawl(2, links[i], "health");
//                    break;
//                case 4:
//                    crawl(2, links[i], "business");
//                    break;
//                case 5:
//                    crawl(2, links[i], "sport");
//                    break;
//                default:
//                    crawl(2, links[i], "style");
//                    break;
//            }
//        }

//        crawl(1, first_link, "politic");


    }

    public void start(){
        thread.start();
    }


    //******* Private Method ********
    // a recursive method that crawls through all the links on a given page to a depth
    private void crawl(int level, String url, String tag){
        if(count <= MAX_ARTICLE && level <= MAX_DEPTH) {
            level++;

            if(!visitedLinks.contains(url)){
                Document document = sendRequest(url);

                if(document != null) {

                    if (document.title().length() > 25) {
                        Database.feed_info(document, source, tag);
                        count += 1;
                    }

                    //call the recursion again on the selected links
                    for(String page : retrieveLinks(document)){
                        crawl(level, page, tag);
                    }
                }
            }
        }
    }


    //this method figures the links for tag
    //return an ArrayList of the links with corresponding tags
    //index     tag
    //  0       politic
    //  1       technology
    //  2       world
    //  3       health
    //  4       business
    //  5       sport
    //  6       style

    private String[] classify(){

        String[] result = new String[7];


        Document doc = sendRequest(first_link);
        for(String link : retrieveLinks(doc)) {
            Document temp = sendRequest(link);

            if (temp.title().length() < 10) {
                if (temp.title().toLowerCase().contains("politic")) {
                    result[0] = link;
                }
                else if (temp.title().toLowerCase().contains("technology")) {
                    result[1] = link;
                }
                else if (temp.title().toLowerCase().contains("world")) {
                    result[2] = link;
                }
                else if (temp.title().toLowerCase().contains("health")) {
                    result[3] = link;
                }
                else if (temp.title().toLowerCase().contains("business")) {
                    result[4] = link;
                }
                else if (temp.title().toLowerCase().contains("sport")) {
                    result[5] = link;
                }
                else if (temp.title().toLowerCase().contains("style")) {
                    result[6] = link; 
                }
            }
        }

        return result;
    }


    //this method request connection to the url
    //returns:
    //  1. The document of the page
    //  2. Null if the connection fails
    private Document sendRequest(String url){
        try{
            Connection connection = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 "
                    + "(KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1");
            Document htmlDocument = connection.get();

            //200 is the HTTP ok status code
            if(connection.response().statusCode() == 200) {
                Log.d("testing", "\n**Visiting** Received webpage at " + url);
//
                String title = htmlDocument.title();
                Log.d("testing", title);
//
//				//add links
                visitedLinks.add(url);

                return htmlDocument;
            }
            return null;
        }
        catch (IOException ioe){
            //System.out.println("not pass");
            Log.d("testing", "not pass");
            return null;
        }
    }

    //this method retrieves links on the web page
    //returns:
    //  1. ArrayList of links
    private ArrayList<String> retrieveLinks(Document doc){
        ArrayList<String> l = new ArrayList<>();

        Elements linksOnPage = doc.select("a[href]");

        for(Element link : linksOnPage){
            l.add(link.absUrl("href"));
        }

        return l;
    }


    //********* Accessors & Modifiers *********
    public Thread getThread(){
        return thread;
    }
}
