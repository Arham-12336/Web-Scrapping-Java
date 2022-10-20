package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Main Main = new Main();
        Main.extract_books_name("http://books.toscrape.com/catalogue/category/books_1/index.html");
    }
    public void concat_urls(String[] books_names) {
        String url = new String("https://books.toscrape.com/catalogue/category/books/");
        String[] names = books_names;
        String domain="/index.html";
         for ( int i = 0; i < books_names.length; i++) {
            String url2 = url + names[i];                        //concatinating the url with books name
            if (i==0) {
                  // not to use the first url
            }
            else {
                  if (url2.endsWith(names[i]))    //checking the url
                  {
                      url2=url2+domain;
                      try {
                          Document URL= (Document) Jsoup.connect(url2).get();
                          Elements product=URL.getElementsByClass("product_pod");
                          Elements title=product.select("h3");
                          Elements price=URL.getElementsByClass("price_color");
                          Elements avail=URL.getElementsByClass("instock availability");
                          System.out.println("Title:"+" "+names[i].split("_")[0]);   //gives the name
                          System.out.println(" "+title.text());      //gives the title of the books
                          System.out.println(" "+price.text());      //gives the price of the books
                          System.out.println(" "+avail.text());      //gives the in stock or out of stock
                          System.out.println(" ");              //just to give a blank space between two sets
                          System.out.println(" ");
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
            }
        }

    }
    public void extract_books_name(String url) throws IOException {
        Document Doc=Jsoup.connect(url).get();
        Elements nav_list=Doc.getElementsByClass("nav-list");
        Elements a=nav_list.select("a");
        String[] books_name=new String[a.size()];
         for( int i=0;i<a.size();i++)    //This loop gets all the url from navlist and stores it in names
        {
            if (books_name[i]=="0")
            {
                return;
            }
            else if (i>0){
                books_name[i] = a.get(i).attr("href"); //gives the list of the links
                books_name[i]=books_name[i].split("/")[2];      //gives the names of all the books
            }
        }
        concat_urls(books_name);
}
}

