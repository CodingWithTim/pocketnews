package com.example.pocketnews;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class Database {
    //parallel ArrayList
    private static ArrayList<String> links = new ArrayList<>();
    private static ArrayList<String> titles = new ArrayList<>();
    private static ArrayList<String> sources = new ArrayList<>();

    //tags are world, politics, technology, health, business, sport, style
    private static ArrayList<String> tags = new ArrayList<>();
    private static ArrayList<Templates> images = new ArrayList<>();

    private ArrayList<Spiderbot> bots = new ArrayList<>();

    //constructor
    public Database(){
        bots.add(new Spiderbot("CNN", "https://cnn.com"));
        bots.add(new Spiderbot("FOX", "https://www.foxnews.com/"));
        bots.add(new Spiderbot("New York Times", "https://www.nytimes.com/"));
        bots.add(new Spiderbot("ABC", "https://abcnews.go.com/"));
        bots.add(new Spiderbot("NBC", "https://www.nbcnews.com/"));
        bots.add(new Spiderbot("MSN", "https://www.msn.com/en-us/news"));
        bots.add(new Spiderbot("NPR", "https://www.npr.org/sections/news/"));
    }

    //********* Synchronized Method *********
    public static synchronized void feed_info(Document doc, String source, String tag){
        links.add(doc.location());
        titles.add(doc.title());
        sources.add(source);
        tags.add(tag);
    }

    //********* Public Method *********
    public ArrayList<String> searchEngine(String s){

        //titles will be added into this array list if there are key words
        ArrayList<String> tempStringList = new ArrayList<String>();
        String tempString = "";
        String searchString = s.replaceAll("\\s", "");

        for(int i = 0; i < titles.size(); ++i){
            tempString = titles.get(i);
            tempString = tempString.replaceAll("\\s", "");
            if(tempString.contains(s)){
                tempStringList.add(titles.get(i));
            }
        }

        return tempStringList;
    }


    //input: String tag name
    //return: an ArrayList of indexes of the tag
    public ArrayList<Integer> getTags(String s){
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i = 0; i < tags.size(); i++){
            if(tags.get(i).equals(s)){
                indexes.add(i);
            }
        }

        return indexes;
    }

    public void update(){
        //reset all other ArrayLists
        links.clear();
        titles.clear();
        sources.clear();
        tags.clear();
        images.clear();

//        for(Spiderbot bot : bots){
//            bot.start();
//        }

        bots.get(0).start();

//        for(Spiderbot bot : bots){
//            try {
//                bot.getThread().join();
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
    }

    //********* Accessors **********
    public static ArrayList<String> getLinks(){
        return links;
    }

    public static ArrayList<String> getTitles(){
        return titles;
    }

    public static ArrayList<String> getSources(){
        return sources;
    }

    public static ArrayList<String> getTags() { return tags; }
}
