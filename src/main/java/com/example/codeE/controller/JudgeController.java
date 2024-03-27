package com.example.codeE.controller;

import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.service.judge.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/judge")
public class JudgeController {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 9998;

    @Autowired
    private ClientService clientService;

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> exampleRq() throws IOException {
        clientService.connect(HOST, PORT);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode packet = JsonNodeFactory.instance.objectNode();

        packet.put("name", "submission-request");
        packet.put("submission-id", "1");
        packet.put("problem-id", "1");
        packet.put("language", "java");
        packet.put("source", "source");
        packet.put("judge-id", "1");
        packet.put("priority", 0);

        // Convert JSON object to string
        String packetString = mapper.writeValueAsString(packet);

        byte[] compressedData = ZlibCompression.zlibify(packetString);

        ByteBuf buffer = Unpooled.buffer(4 + compressedData.length);
        buffer.writeInt(compressedData.length);
        buffer.writeBytes(compressedData);

        clientService.sendRequest(buffer);
        String result = clientService.receiveResponse();
        System.out.println("result: " + result);

        clientService.disconnect();
        return ResponseEntity.status(200).body(result);
    }
}
