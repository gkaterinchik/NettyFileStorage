package ServerSide;

import java.io.Serializable;

public class Request implements Serializable {
    private String id;
    private String fileName;
    private long FileSize;
    private String command;
    private  byte[] content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
        FileSize = fileSize;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
