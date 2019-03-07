package jjr.com.playandroids.beans.fivelistbean;

public class Demo {
    private String nameId;
    private boolean state;
    private int page;

    public Demo(String nameId, boolean state, int page) {
        this.nameId = nameId;
        this.state = state;
        this.page = page;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }
    public boolean getState() {
        return state;
    }
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
