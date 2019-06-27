package com.jiyun.zuoye1.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AndroidBean {
    @Id
    private Long id;
    private String envelopePic;
    private  String title;

    @Generated(hash = 961510861)
    public AndroidBean(Long id, String envelopePic, String title) {
        this.id = id;
        this.envelopePic = envelopePic;
        this.title = title;
    }

    @Generated(hash = 852333117)
    public AndroidBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
