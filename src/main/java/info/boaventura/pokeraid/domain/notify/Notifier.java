package info.boaventura.pokeraid.domain.notify;

import java.time.ZonedDateTime;

public class Notifier implements Runnable {

    private final Thread thread;
    private ZonedDateTime target;

    public Notifier(ZonedDateTime target) {
        this.thread = new Thread(this);
        this.target = target;
    }


    @Override
    public void run() {

    }

    public void start() {
        thread.start();
    }



}
