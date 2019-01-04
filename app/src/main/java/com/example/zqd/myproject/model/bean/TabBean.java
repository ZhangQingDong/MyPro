package com.example.zqd.myproject.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user_tab")
public class TabBean {

    @Id
    @Property(nameInDb = "id")
    @NotNull
    private String id;

    @Property(nameInDb = "name")
    @NotNull
    private String name;

    @Property(nameInDb = "isSelect")
    @NotNull
    private Boolean isSelect;

    @Generated(hash = 293862496)
    public TabBean(@NotNull String id, @NotNull String name,
            @NotNull Boolean isSelect) {
        this.id = id;
        this.name = name;
        this.isSelect = isSelect;
    }

    @Generated(hash = 1439219582)
    public TabBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSelect() {
        return this.isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }
}
