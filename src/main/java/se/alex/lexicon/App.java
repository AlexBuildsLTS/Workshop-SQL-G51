package se.alex.lexicon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");

            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title") + ", Description: " + resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
