package com.openpayd.iyildirim.dto;

import java.util.List;

public class ClientListResponseDTO {
    private List<ClientDTO> clientList;

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }
}
