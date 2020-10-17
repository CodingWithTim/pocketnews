package com.example.pocketnews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class User {
    private HashMap<String, Integer> favTags; //keeps account of how many times the user has viewed an article with certain tags (i.e if a user goes on an article based on gaming, +1 to gaming tag)
    private HashMap<String, Integer> favSource; //same as favTags, but for sources
    private String email;
    private String password;
    private String username;

    public User()
    {
        favTags = new HashMap<String, Integer>();
        favSource = new HashMap<String, Integer>();
        email = "";
        password = "";
        username = "";
    }

    public User(String e, String p, String u) //newly made account.
    {
        favTags = new HashMap<String, Integer>();
        favSource = new HashMap<String, Integer>();
        email = e;
        password = p;
        username = u;
    }

    //Methods
    public void addTag(String s) //adds value to tag that the user has searched up
    {
        if(favTags.containsKey((s))) //If it does contain the key, add to the value +!
        {
            favTags.put(s, favTags.get(s) + 1);
        }
        else //if it does not contain the tag, make a new key
        {
            favTags.put(s,1);
        }
    }

    public void removeTag(String s)
    {
        if(favTags.containsKey(s))
        {
            favTags.remove(s);
        }
        return;
    }

    public void addSource(String s) //adds value to tag that the user has searched up
    {
        if(favSource.containsKey((s))) //If it does contain the key, add to the value +!
        {
            favSource.put(s, favSource.get(s) + 1);
        }
        else //if it does not contain the tag, make a new key
        {
            favSource.put(s,1);
        }
    }

    public void removeSource(String s)
    {
        if(favSource.containsKey(s))
        {
            favSource.remove(s);
        }
        return;
    }

    public ArrayList<String> topTags() //returns an arraylist of the user's top three tags,
    {
        ArrayList<String> tempStringList = new ArrayList<String>(); //gets returned
        ArrayList<Integer> tempIntList = new ArrayList<Integer>();
        ArrayList<Integer> tempIntListTemplate = new ArrayList<Integer>(); //to keep track of original positions
        ArrayList<String> keys = new ArrayList<String>(favTags.keySet());

        //main body: for loop of favTags.size. Compares all key values and gets top 3 biggest values.
        for(int i = 0; i < favTags.size(); ++i)
        { //Gets value of of each tag. top three tags will be returned.

            tempIntList.add(favTags.get(i));

        }

        //Gets top 3 biggest values
        //sorts from least to greatest
        Collections.sort(tempIntList);

        //Getting the three biggest values
        tempIntListTemplate.add(tempIntList.get(tempIntList.size()-1));
        tempIntListTemplate.add(tempIntList.get(tempIntList.size()-2));
        tempIntListTemplate.add(tempIntList.get(tempIntList.size()-3));

        for(int i = 0; i < tempIntListTemplate.size();++i)
        {
            for(int j = 0; j < favTags.size();++j)
            {
                if(tempIntListTemplate.get(i) == favTags.get(j))
                {
                    tempStringList.add(keys.get(j));
                    break;
                }
            }
        }
        return tempStringList;
    }

    //DO THE SAME FOR TOP SOURCES!

    public ArrayList<String> topSources() //returns an arraylist of the user's top three tags,
    {
        ArrayList<String> tempStringList = new ArrayList<String>(); //gets returned
        ArrayList<Integer> tempIntList = new ArrayList<Integer>();
        ArrayList<Integer> tempIntListTemplate = new ArrayList<Integer>(); //to keep track of original positions
        ArrayList<String> keys = new ArrayList<String>(favSource.keySet());
        //main body: for loop of favTags.size. Compares all key values and gets top 3 biggest values.
        for(int i = 0; i < favSource.size(); ++i)
        { //Gets value of of each tag. top three tags will be returned.

            tempIntList.add(favSource.get(i));

        }

        //Gets top 3 biggest values
        Collections.sort(tempIntList); //sorts from least to greatest
        //Getting the three biggest values
        tempIntListTemplate.add(tempIntList.get(tempIntList.size()-1));
        tempIntListTemplate.add(tempIntList.get(tempIntList.size()-2));
        tempIntListTemplate.add(tempIntList.get(tempIntList.size()-3));

        for(int i = 0; i < tempIntListTemplate.size();++i)
        {
            for(int j = 0; j < favSource.size();++j)
            {
                if(tempIntListTemplate.get(i) == favSource.get(j))
                {
                    tempStringList.add(keys.get(j));
                    break;
                }
            }
        }
        return tempStringList;
    }


    //Accessors

    public HashMap<String, Integer> getfavTags()
    {
        return favTags;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public HashMap<String, Integer> getFavSources()
    {
        return favSource;
    }

    //Modifiers
    public void setfavTags  (HashMap<String, Integer> t)
    {
        favTags = t;
    }

    public void setEmail(String s)
    {
        email = s;
    }

    public void setpassword(String s)
    {
        password = s;
    }

    public void setUsername(String s)
    {
        username = s;
    }

    public void setFavSources (HashMap<String, Integer> t)
    {
        favSource = t;
    }
}
