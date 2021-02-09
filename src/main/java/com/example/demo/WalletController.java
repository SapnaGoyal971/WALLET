package com.example.demo;

import com.example.demo.Classes.RequestClass;
import com.example.demo.Classes.Transaction;
import com.example.demo.Classes.TransferDetails;
import com.example.demo.Classes.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    @Autowired
    WalletService walletService;

    @RequestMapping(method = RequestMethod.POST, value = "/wallet")
    public String createWallet(@RequestBody RequestClass request){
        try {
           Wallet wallet = walletService.createWallet(request);
            return "Wallet Created Successfully" + " with User Id: "+wallet.getUserId() ;

        }
        catch (NullPointerException n) {
            return "Wallet with this Phone number already exists";}
        }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction")
    public Transaction.Status getTransactionStatus(@RequestParam Long txnId){
        return walletService.getTransactionStatus(txnId);
       }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction/")
    public Page<Transaction> getTransactionSummary(@RequestParam Long userId, @RequestParam int pageNo){
        return walletService.getTransactionSummary(userId, pageNo);
    }


    @RequestMapping(method = RequestMethod.PUT,value = "/transaction")
    public String transferMoney(@RequestBody TransferDetails transferDetails){
        return walletService.transferMoney(transferDetails.getPayeePhoneNumber(),transferDetails.getPayerPhoneNumber(),transferDetails.getAmount());
    }



}
