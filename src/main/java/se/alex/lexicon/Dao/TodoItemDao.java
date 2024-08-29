package se.alex.lexicon.Dao;

import se.alex.lexicon.model.TodoItem;
import java.util.List;

public interface TodoItemDao {
    TodoItem create(TodoItem todoItem);
    List<TodoItem> findAll();
    TodoItem findById(int id);
    List<TodoItem> findByDoneStatus(boolean isCompleted);
    List<TodoItem> findByAssignee(int personId);
    TodoItem update(TodoItem todoItem);
    boolean deleteById(int id);
}
