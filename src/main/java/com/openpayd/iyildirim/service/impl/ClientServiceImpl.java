package com.openpayd.iyildirim.service.impl;

import com.openpayd.iyildirim.dto.AddressDTO;
import com.openpayd.iyildirim.dto.ClientDTO;
import com.openpayd.iyildirim.dto.ClientListResponseDTO;
import com.openpayd.iyildirim.entity.Address;
import com.openpayd.iyildirim.entity.Client;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.repository.AddressRepository;
import com.openpayd.iyildirim.repository.ClientRepository;
import com.openpayd.iyildirim.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public boolean createClient(ClientDTO clientDTO){
        try {
            Client client = new Client();
            client.setName(clientDTO.getName());
            client.setSurname(clientDTO.getSurname());

            Address primaryAddress = new Address();
            primaryAddress.setAddressLine1(clientDTO.getPrimaryAddress().getAddressLine1());
            primaryAddress.setAddresLine2(clientDTO.getPrimaryAddress().getAddresLine2());
            primaryAddress.setCity(clientDTO.getPrimaryAddress().getCity());
            primaryAddress.setCountry(clientDTO.getPrimaryAddress().getCountry());
            addressRepository.save(primaryAddress);
            client.setPrimaryAddress(primaryAddress);

            Address secondaryAddress = new Address();
            secondaryAddress.setAddressLine1(clientDTO.getSecondaryAddress().getAddressLine1());
            secondaryAddress.setAddresLine2(clientDTO.getSecondaryAddress().getAddresLine2());
            secondaryAddress.setCity(clientDTO.getSecondaryAddress().getCity());
            secondaryAddress.setCountry(clientDTO.getSecondaryAddress().getCountry());
            addressRepository.save(secondaryAddress);
            client.setSecondaryAddress(secondaryAddress);

            clientRepository.save(client);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public ClientListResponseDTO listAllClient(){
        ClientListResponseDTO clientListResponseDTO = new ClientListResponseDTO();
        List<Client> clientList = clientRepository.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientList.forEach(client -> clientDTOS.add(convertEntityToDTO(client)));
        clientListResponseDTO.setClientList(clientDTOS);
        return clientListResponseDTO;
    }

    @Override
    public ClientDTO findClientDetailById(Long clientId) throws RecordNotFoundException {
        return convertEntityToDTO(clientRepository.findById(clientId).orElseThrow(() -> new RecordNotFoundException("Record not found")));
    }
    @Override
    public ClientDTO convertEntityToDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());

        AddressDTO primaryAddressDTO = new AddressDTO();
        AddressDTO secondaryAddressDTO = new AddressDTO();
        primaryAddressDTO.setAddressLine1(client.getPrimaryAddress().getAddressLine1());
        primaryAddressDTO.setAddresLine2(client.getPrimaryAddress().getAddresLine2());
        primaryAddressDTO.setCity(client.getPrimaryAddress().getCity());
        primaryAddressDTO.setCountry(client.getPrimaryAddress().getCountry());
        primaryAddressDTO.setId(client.getPrimaryAddress().getId());

        secondaryAddressDTO.setAddressLine1(client.getSecondaryAddress().getAddressLine1());
        secondaryAddressDTO.setAddresLine2(client.getSecondaryAddress().getAddresLine2());
        secondaryAddressDTO.setCity(client.getSecondaryAddress().getCity());
        secondaryAddressDTO.setCountry(client.getSecondaryAddress().getCountry());
        secondaryAddressDTO.setId(client.getSecondaryAddress().getId());

        clientDTO.setPrimaryAddress(primaryAddressDTO);
        clientDTO.setSecondaryAddress(secondaryAddressDTO);
        return clientDTO;
    }
}
