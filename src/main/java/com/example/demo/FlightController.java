package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class FlightController {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";

    @GetMapping("/flights")
    public List<Flight> flights() {
        return Arrays.asList(createFlight(true), createFlight(false));
    }

    @GetMapping("/flights/flight")
    public Flight flight() {
        return createFlight(true);
    }

    private Flight createFlight(boolean original) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(original ? "Some name" : "Some other name");
        passenger.setLastName(original ? "Some other name" : null);

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);

        Flight flight = new Flight();
        flight.setDeparts(toDate("2017-04-21 14:34"));
        flight.setTickets(Arrays.asList(ticket));
        return flight;
    }

    private Date toDate(String dateTime) {
        LocalDateTime.of(2017, 04, 21, 14, 34);
        try {
            return new SimpleDateFormat(TIME_FORMAT).parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    public static class Flight {
        @JsonProperty("Departs")
        @JsonFormat(pattern = TIME_FORMAT, timezone = "America/Denver")
        private Date departs;

        @JsonProperty("Tickets")
        private List<Ticket> tickets;

        public Date getDeparts() {
            return departs;
        }

        public void setDeparts(Date departs) {
            this.departs = departs;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }
    }

    public static class Ticket {
        @JsonProperty("Passenger")
        private Passenger passenger;

        @JsonProperty("Price")
        private int price;

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public static class Passenger {
        @JsonProperty("FirstName")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String firstName;

        @JsonProperty("LastName")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}