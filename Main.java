public class Main {
    public static void main(String[] args) {
        Battery battery = new Battery();
        DigitalClock clock = new DigitalClock(battery);
        clock.turnOn();
        clock.startClock();
    }
}
