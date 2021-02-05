package com.example.demo;

import com.example.demo.Classes.Transaction;
import com.example.demo.Classes.Wallet;
import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.Repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;


    public String createWallet(Wallet wallet){
        try {
            walletRepository.save(wallet);
            return "Wallet Created Successfully"+ " with User Id: "+wallet.getUserId();

        }
        catch (DataIntegrityViolationException d){
            return "Wallet with this Phone number already exists";
        }
    }

    public String transferMoney(Long payeePhoneNumber, Long payerPhoneNumber, Long amount){

        if(amount<0)
            return "amount cannot be negative";
        try {

            Wallet walletOfPayer = walletRepository.findByPhoneNumber(payerPhoneNumber);
            Wallet walletOfPayee = walletRepository.findByPhoneNumber(payeePhoneNumber);
            Transaction transaction = new Transaction(payeePhoneNumber, payerPhoneNumber, amount);

            if (walletOfPayer.getBalance() >= amount) {

                walletOfPayer.setBalance(walletOfPayer.getBalance() - amount);
                walletOfPayee.setBalance(walletOfPayee.getBalance() + amount);

                walletRepository.save(walletOfPayer);
                walletRepository.save(walletOfPayee);

                transaction.setStatus(Transaction.Status.SUCCESS);
                transactionRepository.save(transaction);

                return "Transfer Successful with transaction Id: "+transaction.getTxnID() ;
            } else {
                transaction.setStatus(Transaction.Status.FAILURE);
                transactionRepository.save(transaction);
                return "Not Sufficient Balance in Payer's Wallet.";
            }

        }
        catch (NullPointerException n){
            return "wrong payer phone number or payee phone number.";
        }
    }

    public Transaction.Status getTransactionStatus(Long transactionId){
        try {
            Transaction transaction = transactionRepository.findByTxnID(transactionId);
            return transaction.getStatus();
        }
        catch (NullPointerException n){
            return null;
        }
    }

    public Page<Transaction> getTransactionSummary(Long userId,int pageNumber){
        try {
            Wallet wallet = walletRepository.findByUserId(userId);
            return transactionRepository.findByPayeePhoneNumberOrPayerPhoneNumber(wallet.getPhoneNumber(), wallet.getPhoneNumber(),PageRequest.of(pageNumber,1));
        }
        catch (NullPointerException n){
            return null;
        }
    }


}
