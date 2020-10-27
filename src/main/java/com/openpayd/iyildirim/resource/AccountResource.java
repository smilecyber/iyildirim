package com.openpayd.iyildirim.resource;

import com.openpayd.iyildirim.dto.AccountDTO;
import com.openpayd.iyildirim.dto.AccountListResponseDTO;
import com.openpayd.iyildirim.dto.ClientDTO;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountListResponseDTO> listAllAccounts(){
        return ResponseEntity.ok().body(accountService.listAllAccount());
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> createClient(@RequestBody AccountDTO accountDTO) throws BadRequestException, RecordNotFoundException {
        return ResponseEntity.ok().body(accountService.createNewAcoount(accountDTO));
    }
}
