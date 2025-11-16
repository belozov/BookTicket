package com.example.ticketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ticketapp.Decorator.KZTPaymentProcessor
import com.example.ticketapp.builder.Ticket
import com.example.ticketapp.facade.BookingFacade
import com.example.ticketapp.factory.*
import com.example.ticketapp.strategy.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val seats = Array(20) { i ->
            Seat(i + 1, if (i % 5 == 0) "VIP" else "Regular", (i % 5) * 50.0)
        }

        setContent {
            TicketBookingScreen(seats)
        }
    }
}

@Composable
fun TicketBookingScreen(seats: Array<Seat>) {
    var userName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("adult") }
    var selectedCurrency by remember { mutableStateOf("KZT") }
    var selectedFactory by remember { mutableStateOf("Concert") }
    var selectedStrategy by remember { mutableStateOf("FrontToBack") }
    var bookedTickets by remember { mutableStateOf(listOf<Ticket>()) }

    val factories = mapOf(
        "Concert" to ConcertTicketFactory(),
        "Movie" to MovieTicketFactory(),
        "Bus" to BusTicketFactory(),
        "Plane" to PlaneTicketFactory(),
        "Train" to TrainTicketFactory()
    )

    val categories = listOf("adult", "child", "student", "pensioner")
    val currencies = listOf("KZT", "USD")
    val strategies = mapOf(
        "FrontToBack" to FrontToBackSeatingStrategy(),
        "CenterBalanced" to CenterBalancedSeatingStrategy(),
        "VIPPriority" to VIPPrioritySeatingStrategy(),
        "Random" to RandomSeatingStrategy(),
        "ChooseBest" to ChooseBestSeatStrategy()
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Ticket Booking System", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))
        DropdownSelector("Category", categories, selectedCategory) { selectedCategory = it }

        Spacer(Modifier.height(8.dp))
        DropdownSelector("Currency", currencies, selectedCurrency) { selectedCurrency = it }

        Spacer(Modifier.height(8.dp))
        DropdownSelector("Event Type", factories.keys.toList(), selectedFactory) { selectedFactory = it }

        Spacer(Modifier.height(8.dp))
        DropdownSelector("Seating Strategy", strategies.keys.toList(), selectedStrategy) { selectedStrategy = it }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val facade = BookingFacade(
                seats.size,
                strategies[selectedStrategy]!!,
                factories[selectedFactory]!!,
                KZTPaymentProcessor(),
                seats
            )

            val ticket = facade.bookTicket(userName, selectedCategory, selectedCurrency)
            ticket?.let {
                bookedTickets = bookedTickets + it
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Book Ticket")
        }

        Spacer(Modifier.height(16.dp))
        Text("Booked Tickets:", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(bookedTickets) { ticket ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("User: ${ticket.userName}")
                        Text("Type: ${ticket.type}")
                        Text("Seat: ${ticket.seatNumber}")
                        Text("Price: ${ticket.price}")
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownSelector(label: String, options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    onSelect(option)
                    expanded = false
                })
            }
        }
    }
}
