package se.alex.lexicon;

import se.alex.lexicon.Dao.impl.PersonDaoJdbc;
import se.alex.lexicon.Dao.impl.TodoItemDaoJdbc;
import se.alex.lexicon.model.Person;
import se.alex.lexicon.model.TodoItem;

public class App {
    public static void main(String[] args) {
        // Initialize DAOs
        PersonDaoJdbc personDao = new PersonDaoJdbc();
        TodoItemDaoJdbc todoItemDao = new TodoItemDaoJdbc();


        // Define the name you want to add
        String firstName = "Alex";
        String lastName = "Lexicon";

        // Check if the person already exists
        Person existingPerson = personDao.findByName("Alex", "Lexicon");

        if (existingPerson == null) {
            // Create and save a new person if they don't exist
            Person person = new Person(0, "Alex", "Lexicon");
            personDao.create(person);
            System.out.println("Person created: " + person);
        } else {
            // If the person exists, update or skip as needed
            System.out.println("Person already exists: " + existingPerson);
        }


        if (existingPerson == null) {
            // Create and save a new person if they don't exist
            Person person = new Person(0, firstName, lastName);
            personDao.create(person);
            System.out.println("Person created: " + person);
        } else {
            System.out.println("Person already exists: " + existingPerson);
            // existingPerson.setLastName("NewLastName");
            // personDao.update(existingPerson);
        }

        // Create and save a todo item
        TodoItem todoItem = new TodoItem(0, "Task 1", "Description 1", false);
        todoItemDao.create(todoItem);

        // Find and display all persons
        System.out.println(personDao.findAll());

        // Find and display all todo items
        System.out.println(todoItemDao.findAll());


    }
}
