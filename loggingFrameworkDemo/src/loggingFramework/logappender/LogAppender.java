package loggingFramework.logappender;

import loggingFramework.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
