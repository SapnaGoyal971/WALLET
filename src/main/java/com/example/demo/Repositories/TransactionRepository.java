package com.example.demo.Repositories;

import com.example.demo.Classes.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    public Transaction findByTxnID(Long id);
    public List<Transaction> findByPayeePhoneNumber(Long phn);
    public List<Transaction> findByPayerPhoneNumber(Long phn);
}
