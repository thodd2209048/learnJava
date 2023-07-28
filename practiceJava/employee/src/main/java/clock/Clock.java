package clock;

public class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public Clock() {
    }

    public Clock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void tick() {
        seconds += 1;
    }

    public void setClock(int s) {
        int h = s / 3600;
        h %= 24;
        s = s % 3600;
        int m = s / 60;
        s = s % 60;
        System.out.println(h + " : " + m + " : " + s);
    }

    public int toSeconds(Clock clock) {
        return clock.getHours() * 3600 + clock.getMinutes() * 60 + clock.getSeconds();
    }
    public void addClock (Clock addTime) {
        int addTimeS = addTime.toSeconds(addTime);
        int currentS = this.toSeconds(this);
        int sumS =  currentS + addTimeS;
        setClock(sumS);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
