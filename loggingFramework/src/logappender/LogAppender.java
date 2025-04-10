package logappender;

import LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
