import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.TreeMultimap;

import java.util.*;

public class SocialNetwork {

    private Map<String, Person> peopleByName = new HashMap<>();
    private Map<Long, Person> peopleById = new HashMap<>();
    private BiMap<Person, Person> couples = HashBiMap.create();
    private TreeMultimap<Person, Person> friendships = TreeMultimap.create();


    public void addPerson(Person person) {
        peopleByName.put(person.getName(), person);
        peopleById.put(person.getId(), person);

    }

    public Person getPerson(Long id) {
        return peopleById.get(id);
    }

    public Person getPerson(String name) {
        return peopleByName.get(name);
    }

    public void addCouple(Person person, Person anotherperson) {
        chekIfCouple(person);
        chekIfCouple(anotherperson);
        couples.put(person, anotherperson);
    }

    private void chekIfCouple(Person person) {
        if (couples.containsKey(person) || couples.values().contains(person)) {
            System.out.println("La persona " + person.getName() + "ya tiene pareja");
            throw new RuntimeException("La persona " + person.getName() + "ya tiene pareja");
        }
    }

    public Person getCouple(Person person) {
        Person couple = couples.get(person);
        //búsqueda por key
        if (couple != null) {
            return couple;
        } else {
            couple = couples.inverse().get(person);
            //búsqueda por value
            return couple;
        }
    }

    public void addFriendship(Person person, Person anotherPerson) {

        checkFrienship(person, anotherPerson);

        friendships.put(person, anotherPerson);
        friendships.put(anotherPerson, person);
    }

    public void addFriendship(Person person, Person... friends) {

        for (Person friend : friends) {
            addFriendship(person, friend);
        }
    }

    private void checkFrienship(Person person, Person anotherPerson) {
        if (friendships.containsKey(person)
                && friendships.get(person).contains(anotherPerson)) {
            System.out.println(anotherPerson.getName() + " ya es amigo de " + person.getName());
            throw new RuntimeException(anotherPerson.getName() + " ya es amigo de " + person.getName());
        }
    }

    //Set y no List para que no haya amigos duplicados

    public Set<Person> getFriendship(Person person) {

        Set<Person> friends = friendships.get(person);
        if (friends != null) //por si no tiene amigos.
        {
            return friends;
        } else return new TreeSet<>();
    }


    public Set<Person> getCouplesFriends(Person person) {

        Person couple = getCouple(person);

        if (couple == null) {
            System.out.println("La persona" + person.getName() + "no tiene pareja.");
            return new TreeSet<Person>();
        } else {
            return getFriendship(couple);
        }
    }

    public Set<Person> getFriendsCouples(Person person) {

        Set<Person> friends = getFriendship(person);
        Set<Person> couples = new HashSet<>();
        for (Person friend : friends) {
            Person couple = getCouple(friend);
            if (couple != null) {
                couples.add(couple);
            }
        }

        return couples;

    }

    public Integer getNumberFriends(Person person) {
        return getFriendship(person).size();
    }


    public List<Person> popularity(Person person) {
        List<Person> personList = new ArrayList<>(peopleByName.values());
        Collections.sort(personList, new Comparator<Person>() {

            @Override
            public int compare(Person person1, Person person2) {
                int numFriends1 = getNumberFriends(person1);
                int numFriends2 = getNumberFriends(person2);
                if (numFriends1 < numFriends2) {
                    return 1;
                }
                if (numFriends1 > numFriends2) {
                    return -1;

                } else return 0;
            }
        });
        return personList;
    }



    public int getConnectionDegree(Person p1, Person p2) {
        return 0;
    }

    public SortedSet<Person> getConnectionDegreePath
            (Person p1, Person p2) {
        return null;
    }



}