package jjr.com.playandroids.beans.fivelistbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchDetails {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String title;
    private String time;
    @Generated(hash = 1612823923)
    public SearchDetails(Long id, String name, String title, String time) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.time = time;
    }
    @Generated(hash = 1950652942)
    public SearchDetails() {
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
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
