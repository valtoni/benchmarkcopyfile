package info.boaventura.pokeraid.domain.notify;

public interface Notifiable {


    Notification register();
    void fire();

}
