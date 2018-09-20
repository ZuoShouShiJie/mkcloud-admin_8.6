//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package mkcloudadmin.permission.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuTreeRes {
    private int id;
    private String uuid;
    private String text;
    private List<mkcloudadmin.permission.vo.MenuTreeRes> children;
    private Map<String, Object> state;
    private String type;

    public MenuTreeRes() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<mkcloudadmin.permission.vo.MenuTreeRes> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList();
        }

        return this.children;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChildren(List<mkcloudadmin.permission.vo.MenuTreeRes> childMenu) {
        this.children = childMenu;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Map<String, Object> getState() {
        return this.state;
    }

    public void setState(Map<String, Object> state) {
        this.state = state;
    }
}
