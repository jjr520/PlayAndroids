package jjr.com.playandroids.beans.fivelistbean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CollectUtils {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String title;
    private String link;
    private String content;
    private int postion;
    private boolean state;
    @Generated(hash = 617247366)
    public CollectUtils(Long id, String name, String title, String link,
            String content, int postion, boolean state) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.link = link;
        this.content = content;
        this.postion = postion;
        this.state = state;
    }
    @Generated(hash = 1179078902)
    public CollectUtils() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPostion() {
        return this.postion;
    }
    public void setPostion(int postion) {
        this.postion = postion;
    }
    public boolean getState() {
        return this.state;
    }
    public void setState(boolean state) {
        this.state = state;
    }



}
