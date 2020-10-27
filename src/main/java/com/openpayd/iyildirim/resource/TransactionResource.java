package com.openpayd.iyildirim.resource;

import com.openpayd.iyildirim.dto.TransactionDTO;
import com.openpayd.iyildirim.dto.TransactionListResponseDTO;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.service.TransactionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/v1")
public class TransactionResource {
    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<TransactionListResponseDTO> getEmployeeById(@PathVariable(value = "accountId") Long accountId)
            throws RecordNotFoundException {
        TransactionListResponseDTO transactionListResponseDTO = transactionService.listAccountTransactionList(accountId);
        return ResponseEntity.ok().body(transactionListResponseDTO);
    }

    @PostMapping(value = "/makeTransfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> createClient(@RequestBody TransactionDTO transactionDTO) throws BadRequestException, RecordNotFoundException {
        return ResponseEntity.ok(transactionService.makeTransfer(transactionDTO));
    }
}
