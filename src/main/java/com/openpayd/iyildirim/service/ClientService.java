package com.openpayd.iyildirim.service;

import com.openpayd.iyildirim.dto.ClientDTO;
import com.openpayd.iyildirim.dto.ClientListResponseDTO;
import com.openpayd.iyildirim.entity.Client;
import com.openpayd.iyildirim.exception.RecordNotFoundException;

public interface ClientService {
    boolean createClient(ClientDTO clientDTO);

    ClientListResponseDTO listAllClient();

    ClientDTO findClientDetailById(Long clientId) throws RecordNotFoundException;

    ClientDTO convertEntityToDTO(Client client);
}
