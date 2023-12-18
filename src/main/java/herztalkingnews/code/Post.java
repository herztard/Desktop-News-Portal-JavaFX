package herztalkingnews.code;

public class Post {
    private String header;
    private String content;
    private String date;
    private String image;

    Post(String header, String content, String date){
        this.header = header;
        this.content = content;
        this.date = date;
    }

    Post(String header, String content, String date, String image){
        this.header = header;
        this.content = content;
        this.date = date;
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
