package info.boaventura.pokeraid.domain;

import info.boaventura.pokeraid.domain.notify.Notifiable;
import info.boaventura.pokeraid.domain.notify.Notification;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class RaidGame implements Notifiable {

    private Raid raid;
    private Set<Player> participants = new HashSet<>();
    private ZonedDateTime startTime;
    private Duration tolerance;


    @Override
    public Notification register() {
        return new Notification();
    }

    @Override
    public void fire() {

    }

}
