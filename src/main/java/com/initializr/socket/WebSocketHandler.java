/**
 * MIT License
 Copyright (c) 2018 deepakshajan
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package com.initializr.socket;

import com.google.gson.Gson;
import com.initializr.backbone.SBMSWebSocketRequest;
import com.initializr.service.request.DeployServiceClusterServiceRequestImpl;
import com.initializr.socket.request.SBMSWebSocketRequestImpl;
import com.initializr.socket.response.SBMSWebSocketResponseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * @author Deepak Shajan
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeployServiceClusterServiceRequestImpl deployServiceClusterServiceRequest;

    @Autowired
    private WebSocketRequestDispatcher webSocketRequestDispatcher;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();
        SBMSWebSocketRequest request = new Gson().fromJson(payload, SBMSWebSocketRequestImpl.class);

        String response = webSocketRequestDispatcher.redirect(request);

        session.sendMessage(new TextMessage("Message is : "+ response.toString()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }


    public void sendMessageToAllWebSocketSessions(SBMSWebSocketResponseImpl message) {

        for(WebSocketSession session : sessions) {
            try {
                String jsonMessage = new Gson().toJson(message);
                session.sendMessage(new TextMessage(jsonMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void getServiceRequest(SBMSWebSocketRequest request) {

        deployServiceClusterServiceRequest.setClusterPath(request.getClusterPath().replaceAll(Pattern.quote("\\\\"),"\\"));
        deployServiceClusterServiceRequest.setBuildType(request.getBuildType());
        deployServiceClusterServiceRequest.setRunBoot(request.isRunBoot());
        deployServiceClusterServiceRequest.setRunClean(request.isRunClean());
        deployServiceClusterServiceRequest.setRunTests(request.isRunTests());
    }

}



