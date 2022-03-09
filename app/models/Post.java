package models;

public class Post {
    public Integer id;
    public String title;
    public String body;
    public Integer userId;

    public Post(Integer id, String title, String body, Integer userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
