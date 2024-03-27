package com.example.codeE.service.judge;

import io.netty.buffer.ByteBuf;

public interface ClientService {
    public void connect(String host, int port);
    public void disconnect();
    public void sendRequest(ByteBuf buffer);
    public String receiveResponse();
}
