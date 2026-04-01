package utils;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class OutputListener extends Writer {
    //Message history of program System.out.println();
    private final List<String> messageHistory = new ArrayList<>();
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        //Just pass args into a new string
        String message = new String(cbuf,off,len);
        messageHistory.add(message);
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
    //Get message history make a copy
    public List<String> getMessages(){
        return new ArrayList<>(messageHistory);
    }
}
