package jjr.com.playandroids.beans.knowbean;

/**
 * Created by jjr on 2019/3/1.
 */

public class EventBusBean {
    private String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventBusBean{" +
                "event='" + event + '\'' +
                '}';
    }
}
