package com.example.codeE.service.judge;

import com.example.codeE.helper.ZlibCompression;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Service
public class ClientImpl implements ClientService{
    Connection connection;
    @Override
    public void connect(String host, int port) {
        connection = TcpClient.create()
                .host(host)
                .port(port)
                .doOnConnected(connection1 -> {
                    System.out.println("Connected");
                })
                .connectNow();
    }

    @Override
    public void disconnect() {
        connection.onDispose()
                .block();
    }

    @Override
    public void sendRequest(ByteBuf buffer) {
        connection
                .outbound()
                .send(Mono.just(buffer))
                .then()
                .subscribe();
    }

    @Override
    public String receiveResponse() {
        return connection.inbound()
                .receive()
                .asByteArray()
                .take(1) // Take only one response
                .doOnTerminate(connection::dispose) // Dispose the connection after receiving the response
                .map(response -> {
                    try {
                        System.out.println("Received data from server: " + ZlibCompression.dezlibify(response, false));
                        return ZlibCompression.dezlibify(response, false);
                    } catch (DataFormatException | IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .blockFirst();
    }
}
