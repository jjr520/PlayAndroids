package jjr.com.playandroids.beans.fivelistbean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TreeBean {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    @Generated(hash = 813915862)
    public TreeBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1060265969)
    public TreeBean() {
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
}
