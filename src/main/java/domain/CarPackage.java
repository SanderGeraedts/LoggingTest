package domain;

import java.io.Serializable;

public class CarPackage implements Serializable {
    private Long time;

    public CarPackage(Long time) {
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time.toString();
    }
}
