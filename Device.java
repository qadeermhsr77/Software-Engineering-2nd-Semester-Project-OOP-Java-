// Inheritance
class Device {
    String brand;
    void turnOn() {
        System.out.println("Device is turned ON.");
    }
}

// Composition
class Display {
    void showTime(String time) {
        System.out.println("Time: " + time);
    }
}

class Alarm {
    String alarmTime = "20:53";
    void checkAlarm(String currentTime) {
        if (currentTime.equals(alarmTime)) {
            System.out.println("⏰ Alarm Ringing!");
        }
    }
}

// Aggregation
class Battery {
    int chargeLevel = 100;
    void useBattery() {
        chargeLevel -= 1;
    }
}

// Inheritance continues
class DigitalClock extends Device {
    Display display = new Display();         // composition
    Alarm alarm = new Alarm();               // composition
    Battery battery;                         // aggregation

    public DigitalClock(Battery battery) {
        this.battery = battery;
    }

    void startClock() {
        ClockThread clockThread = new ClockThread(display, alarm, battery);
        clockThread.start();
    }
}

// Threading
class ClockThread extends Thread {
    Display display;
    Alarm alarm;
    Battery battery;

    ClockThread(Display d, Alarm a, Battery b) {
        display = d;
        alarm = a;
        battery = b;
    }

    public void run() {
        try {
            while (true) {
                java.time.LocalTime time = java.time.LocalTime.now();
                String currentTime = time.getHour() + ":" + String.format("%02d", time.getMinute());
                display.showTime(currentTime);
                alarm.checkAlarm(currentTime);
                battery.useBattery();
                Thread.sleep(60000); // wait 1 minute
            }
        } catch (InterruptedException e) {
            System.out.println("Clock stopped.");
        }
    }
}
