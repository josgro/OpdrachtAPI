package nl.codegorilla.OpdrachtAPI.controller;

import nl.codegorilla.OpdrachtAPI.dataService.DataService;
import nl.codegorilla.OpdrachtAPI.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final DataService dataService;

    @Autowired
    public ClientController(DataService dataService) {
        this.dataService = dataService;
        dataService.initializeDB();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = dataService.findAllClients();
        return ResponseEntity.status(200).body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") int id) {
        Client client = dataService.findClientById(id);
        if (client == null) {
            return ResponseEntity.status(400).body("Error, id not found.");
        } else {
            return ResponseEntity.status(200).body(client);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        int flag = dataService.addClient(client);
        if (flag >= 1) {
            return ResponseEntity.status(201).body("Client created.");
        } else if (flag == 0) {
            return ResponseEntity.status(500).body("Client not created. Something went wrong.");
        } else {
            return ResponseEntity.status(400).body("Client not created. All fields must be filled.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        int flag = dataService.updateClient(client);
        if (flag >= 1) {
            return ResponseEntity.status(201).body("Client updated.");
        } else if (flag == 0) {
            return ResponseEntity.status(500).body("Client not created. Something went wrong.");
        } else {
            return ResponseEntity.status(400).body("Client not updated. All fields must be filled.");
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") int id) {
        int flag = dataService.deleteClient(id);
        if (flag >= 1) {
            return ResponseEntity.status(200).body("Client deleted.");
        } else if (flag == 0) {
            return ResponseEntity.status(500).body("Client not found.");
        } else {
            return ResponseEntity.status(400).body("Id can't be 0.");
        }
    }

}
