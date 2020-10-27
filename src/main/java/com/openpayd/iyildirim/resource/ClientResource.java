package com.openpayd.iyildirim.resource;

import com.openpayd.iyildirim.dto.ClientDTO;
import com.openpayd.iyildirim.dto.ClientListResponseDTO;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1")
public class ClientResource {
    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<ClientListResponseDTO> listAllClients() {
        return ResponseEntity.ok().body(clientService.listAllClient());

    }

    @PostMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) throws BadRequestException {
        if (clientService.createClient(clientDTO)) {
            return ResponseEntity.ok().body(clientDTO);
        } else {
            throw new BadRequestException("Request format invalid");
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getEmployeeById(@PathVariable(value = "id") Long clientId)
            throws RecordNotFoundException {
        ClientDTO clientDTO = clientService.findClientDetailById(clientId);
        return ResponseEntity.ok().body(clientDTO);
    }
}
