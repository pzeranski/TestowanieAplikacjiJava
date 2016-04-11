package com.pzeranski;


public interface MessageService {
    ConnectionStatus checkConnection(String server);

    SendingStatus send(String server, String contents) throws MalformedRecipientException;
}
