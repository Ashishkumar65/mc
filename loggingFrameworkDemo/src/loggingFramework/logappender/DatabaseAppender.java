package loggingFramework.logappender;

import loggingFramework.LogMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseAppender implements LogAppender {
    private final String jdbcUrl;
    private final String userName;
    private final String password;
    public DatabaseAppender(String jdbcUrl,String userName, String password){
        this.jdbcUrl = jdbcUrl;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void append(LogMessage logMessage) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, userName, password)) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (level, message, timestamp) VALUES (?, ?, ?)")) {
                statement.setString(1, logMessage.getLogLevel().toString());
                statement.setString(2, logMessage.getMessage());
                statement.setLong(3, logMessage.getTimeStamp());
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
