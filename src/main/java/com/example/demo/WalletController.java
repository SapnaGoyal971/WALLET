package com.example.demo;

import com.example.demo.Classes.Request;
import com.example.demo.Classes.Transaction;
import com.example.demo.Classes.TransferDetails;
import com.example.demo.Classes.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    WalletService walletService;

    @RequestMapping(method = RequestMethod.POST, value = "/wallet")
    public String createWallet(@RequestBody Request request){
        Wallet wallet = new Wallet(request.getPhoneNumber());
           return walletService.createWallet(wallet);
        }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction/{transactionId}")
    public Transaction.Status getTransactionStatus(@PathVariable Long transactionId){
        return walletService.getTransactionStatus(transactionId);
       }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction/{userId}")
    public List<Transaction> getTransactionSummary(@PathVariable Long userId){
        return walletService.getTransactionSummary(userId);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/transaction")
    public String transferMoney(@RequestBody TransferDetails transferDetails){
        return walletService.transferMoney(transferDetails.getPayeePhoneNumber(),transferDetails.getPayerPhoneNumber(),transferDetails.getAmount());
    }



}
