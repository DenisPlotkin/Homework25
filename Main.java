import java.sql.SQLException;

public class Main {
    private static final Repository REPOSITORY = new Repository();

    public static void main(String[] args) throws SQLException {
        User user = new User("01641361-1cab-4a21-8aaa-86e8cb1a6ed2", "Den Plotkin", 28);
        JDBCConfig.getConnection();
        REPOSITORY.insert(user);
        REPOSITORY.select(user.getId());
        REPOSITORY.update(user.getId(), "Denis Plotkin", 28);
        REPOSITORY.delete(user.getId());
    }
}
