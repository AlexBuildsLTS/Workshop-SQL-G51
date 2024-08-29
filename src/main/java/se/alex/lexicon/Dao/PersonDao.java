package se.alex.lexicon.Dao;

import se.alex.lexicon.model.Person;
import java.util.List;

public interface PersonDao {
    Person create(Person person);
    List<Person> findAll();
    Person findById(int id);
    List<Person> findByName(String name);
    Person update(Person person);
    boolean deleteById(int id);
}
