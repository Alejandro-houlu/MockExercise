package sg.edu.nus.iss.MockExercise.Model;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Book")
public class Book implements Serializable{

    
    private static final long serialVersionUID = 1L;
    private String id;
    private String title; 
    private String author;
    private String thumbnailUrl;

    public Book(String title, String author, String thumbnailUrl){
        this.title = title;
        this.author = author;
        this.thumbnailUrl = thumbnailUrl;

        this.id = setUniqueId();
    }

    public Book(){}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" + "Author: " + author;
    }

    public String setUniqueId(){
        
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId.substring(0,8);
    }



    
}
