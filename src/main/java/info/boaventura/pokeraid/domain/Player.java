package info.boaventura.pokeraid.domain;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private String code;
    private String nick;
    private Set<Group> groups = new HashSet<>();

}
