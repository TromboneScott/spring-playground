package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testFlightsEndpoint() throws Exception {
        mvc.perform(get("/flights")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Some other name")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.LastName").doesNotExist())
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(200)));
    }

    @Test
    public void testFlightEndpoint() throws Exception {
        mvc.perform(get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void testTotalEndpointStringLiteral() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "    \"tickets\": [\n" +
                        "      {\n" +
                        "        \"passenger\": {\n" +
                        "          \"firstName\": \"Some name\",\n" +
                        "          \"lastName\": \"Some other name\"\n" +
                        "        },\n" +
                        "        \"price\": 200\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"passenger\": {\n" +
                        "          \"firstName\": \"Name B\",\n" +
                        "          \"lastName\": \"Name C\"\n" +
                        "        },\n" +
                        "        \"price\": 150\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");
        FlightController.Passenger passenger1 = new FlightController.Passenger();
        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");
        FlightController.Ticket ticket1 = new FlightController.Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);

        FlightController.Passenger passenger2 = new FlightController.Passenger();
        passenger2.setFirstName("Name B");
        passenger2.setLastName("Name C");
        FlightController.Ticket ticket2 = new FlightController.Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(150);

        String fromObject = new String(new ObjectMapper().writeValueAsBytes(Arrays.asList(ticket1, ticket2)));
        System.out.println("fromObject: " + fromObject);
        System.out.println("straight: " + "  {\n" +
                "    \"tickets\": [\n" +
                "      {\n" +
                "        \"passenger\": {\n" +
                "          \"firstName\": \"Some name\",\n" +
                "          \"lastName\": \"Some other name\"\n" +
                "        },\n" +
                "        \"price\": 200\n" +
                "      },\n" +
                "      {\n" +
                "        \"passenger\": {\n" +
                "          \"firstName\": \"Name B\",\n" +
                "          \"lastName\": \"Name C\"\n" +
                "        },\n" +
                "        \"price\": 150\n" +
                "      }\n" +
                "    ]\n" +
                "  }");

        request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"Passenger\":{\"FirstName\":\"Some name\",\"LastName\":\"Some other name\"},\"Price\":200},{\"Passenger\":{\"FirstName\":\"Name B\",\"LastName\":\"Name C\"},\"Price\":150}]");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testTotalEndpointObject() throws Exception {
        FlightController.Passenger passenger1 = new FlightController.Passenger();
        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");
        FlightController.Ticket ticket1 = new FlightController.Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(200);

        FlightController.Passenger passenger2 = new FlightController.Passenger();
        passenger2.setFirstName("Name B");
        passenger2.setLastName("Name C");
        FlightController.Ticket ticket2 = new FlightController.Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(150);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(Arrays.asList(ticket1, ticket2)));
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testTotalEndpointFile() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJSON("/total.json"));
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    private String getJSON(String path) throws Exception {
        return new String(Files.readAllBytes(Paths.get(getClass().getResource(path).getFile())));
    }
}