package cs112.ud3;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * If the internet knows what they're talking about, this should solve the corruption issue with writing objects
 * to an appended file, by creating a writeobject version that doesn't include a header.
 */
public class NoHeaderObjectOutputStream extends ObjectOutputStream {
    public NoHeaderObjectOutputStream() throws IOException{
        super();
    }
    public NoHeaderObjectOutputStream(OutputStream o) throws IOException{
        super(o);
    }

    protected void writeStreamHeader() throws IOException{

    }
}
