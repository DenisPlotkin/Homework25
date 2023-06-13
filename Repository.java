import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Repository {

    private static Repository instance;
    private final Connection connection;

    public Repository() {
        connection = JDBCConfig.getConnection();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public boolean select(String id) throws SQLException {
        final String sql = "SELECT * from user WHERE  id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public boolean delete(String id) throws SQLException {
        final String sql = "DELETE FROM user WHERE id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public boolean insert(User user) throws SQLException {
        final String sql = "INSERT INTO user (id, name, age) VALUES (?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public boolean update(String id, String name, int age) throws SQLException {
        final String sql = "UPDATE user SET name = ?, age = ? VALUES(?, ?) WHERE id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }
}
