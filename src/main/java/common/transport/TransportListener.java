package common.transport;

import java.io.IOException;

//интерфейс слущателя
public interface TransportListener {
    public TransportConnection accept() throws IOException;

    public void close() throws IOException;
}
