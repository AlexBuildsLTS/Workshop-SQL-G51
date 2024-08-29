package se.alex.lexicon.Dao.impl;

import se.alex.lexicon.Dao.TodoItemDao;
import se.alex.lexicon.model.TodoItem;
import se.alex.lexicon.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemDaoJdbc implements TodoItemDao {
    @Override
    public TodoItem create(TodoItem todoItem) {
        String query = "INSERT INTO todo_item (title, description, is_completed) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setBoolean(3, todoItem.isCompleted());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    todoItem.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                todoItems.add(new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("is_completed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public TodoItem findById(int id) {
        TodoItem todoItem = null;
        String query = "SELECT * FROM todo_item WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    todoItem = new TodoItem(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("is_completed")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public List<TodoItem> findByDoneStatus(boolean isCompleted) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE is_completed = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, isCompleted);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    todoItems.add(new TodoItem(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("is_completed")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public List<TodoItem> findByAssignee(int personId) {
        List<TodoItem> todoItems = new ArrayList<>();
        String query = "SELECT * FROM todo_item WHERE todo_item.id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    todoItems.add(new TodoItem(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("is_completed")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItems;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        String query = "UPDATE todo_item SET title = ?, description = ?, is_completed = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setBoolean(3, todoItem.isCompleted());
            preparedStatement.setInt(4, todoItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM todo_item WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
