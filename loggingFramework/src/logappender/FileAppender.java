package logappender;

import java.io.FileWriter;


public class FileAppender implements LogAppender{
    private final String filePath;
    public FileAppender(String filePath){
        this.filePath = filePath;
    }
    @Override
    public void append(LogMessage logMessage) {
        try(FileWriter writer = new FileWriter(filePath,true)){
            writer.write(logMessage.toString() + "\n");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
