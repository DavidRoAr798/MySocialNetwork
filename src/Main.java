import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static SocialNetwork socialNetwork = new SocialNetwork();

    public static void main(String[] args) {

        initializeSocialNetwork();

        Person cristina = socialNetwork.getPerson("Cristina");
        Person juan = socialNetwork.getPerson("Juan");
        Person ana = socialNetwork.getPerson("Ana");
        Person marc = socialNetwork.getPerson("Marc");
        Person antonio = socialNetwork.getPerson("Antonio");
        Person pedro = socialNetwork.getPerson("Pedro");
        Person carol = socialNetwork.getPerson("Carol");
        Person julia = socialNetwork.getPerson("Julia");

        socialNetwork.addCouple(ana, marc);
        socialNetwork.addCouple(pedro, antonio);
        socialNetwork.addCouple(cristina, juan);

        socialNetwork.addFriendship(juan, marc);
        socialNetwork.addFriendship(julia, ana, carol);
        socialNetwork.addFriendship(antonio, ana, marc, juan);
        socialNetwork.addFriendship(pedro, julia, cristina);

        // socialNetwork.addCouple(pedro, ana); // ejemplo de error

        System.out.println("La pareja de Pedro es: " + socialNetwork.getCouple(pedro));
        System.out.println("La pareja de Antonio es: " + socialNetwork.getCouple(antonio));

        System.out.println("Los amigos de Juan son: " + socialNetwork.getFriendship(juan));

        System.out.println("Los amigos de la pareja de Juan son: " + socialNetwork.getCouplesFriends(juan));

        System.out.println("Las parejas de los amigos de Juan" + socialNetwork.getFriendsCouples(juan));

        System.out.println("El numero de amigos de Antonio" + socialNetwork.getNumberFriends(antonio));

        System.out.println("El ranking de popularidad es: " + socialNetwork.popularity(juan));

        System.out.println("Query of person by Id:");
        System.out.println("Id 1: "+socialNetwork.getPerson(1L));

        System.out.println("Query of person by Name ");
        System.out.println("Name Cristina:"
                +socialNetwork.getPerson("Cristina"));


    }

    private static void
    initializeSocialNetwork() {
        Person cristina = new Person(1L,"Cristina","Duran");
        Person juan = new Person(2L,"Juan","Martínez");
        Person ana = new Person(3L,"Ana","Gómez");
        Person marc = new Person(4L,"Marc","Fuentes");
        Person antonio = new Person(5L,"Antonio","Martínez");
        Person pedro = new Person(6L,"Pedro","González");
        Person carol = new Person(7L, "Carol", "Ñu");
        Person julia = new Person(8L, "Julia", "Pi");


        socialNetwork.addPerson(cristina);
        socialNetwork.addPerson(juan);
        socialNetwork.addPerson(ana);
        socialNetwork.addPerson(marc);
        socialNetwork.addPerson(antonio);
        socialNetwork.addPerson(pedro);
        socialNetwork.addPerson(carol);
        socialNetwork.addPerson(julia);
    }


}