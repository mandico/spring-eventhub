package com.azure.spring.sample.eventgrid;

import com.azure.core.util.BinaryData;
import com.azure.messaging.eventgrid.EventGridPublisherClient;
import com.azure.messaging.eventgrid.EventGridEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventGridController {

    @Autowired
    EventGridPublisherClient<EventGridEvent> client;
    @GetMapping("/publish")
    public String publish() {

        // Create a EventGridEvent with String data
        String str = "FirstName: Luiz, LastName: Mandico";
        EventGridEvent event = new EventGridEvent("A user is created", "User.Created.Text", BinaryData.fromObject(str), "1.0");

        client.sendEvent(event);

        return "Publishing succeeded.";
    }
}
