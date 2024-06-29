
package com.lang.streams;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// https://www.baeldung.com/java9-stream-collectors
// https://www.baeldung.com/java-groupingby-collector

public class GroupByTest {

    
   private static class BlogPost {
        String title;
        String author;
    
       public String getTitle() {
           return title;
       }
    
       public BlogPost setTitle(String title) {
           this.title = title;
           return this;
       }
    
       public String getAuthor() {
           return author;
       }
    
       public BlogPost setAuthor(String author) {
           this.author = author;
           return this;
       }
    
       public BlogPostType getType() {
           return type;
       }
    
       public BlogPost setType(BlogPostType type) {
           this.type = type;
           return this;
       }
    
       public int getLikes() {
           return likes;
       }
    
       public BlogPost setLikes(int likes) {
           this.likes = likes;
           return this;
       }
    
       BlogPostType type;
        int likes;
    }
    
   private static enum BlogPostType {
        NEWS,
        REVIEW,
        GUIDE
    }
    
    
    public static void main(String[] args) {
    
        BlogPost newsPost = new BlogPost();
        newsPost.title = "Breaking News: New Technology Released!";
        newsPost.author = "John Doe";
        newsPost.type = BlogPostType.NEWS;
        newsPost.likes = 102;
    
        BlogPost reviewPost = new BlogPost();
        reviewPost.title = "Product Review: XYZ Smartphone";
        reviewPost.author = "Jane Smith";
        reviewPost.type = BlogPostType.REVIEW;
        reviewPost.likes = 45;
    
        BlogPost guidePost = new BlogPost();
        guidePost.title = "Beginner's Guide to Java Programming";
        guidePost.author = "Michael Johnson";
        guidePost.type = BlogPostType.GUIDE;
        guidePost.likes = 78;
    
        List<BlogPost> blogPosts = List.of(reviewPost, guidePost, newsPost);
    
        Map<String, List<BlogPostType>> collect = blogPosts.stream()
            .collect(groupingBy(BlogPost::getAuthor, mapping(BlogPost::getType,
                Collectors.toList())));
    
        System.out.println(collect.toString());
    
    
    }
}
