package com.example.demo.Repositories;

import com.example.demo.Classes.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    public Transaction findByTxnID(Long id);
    public Page<Transaction> findByPayeePhoneNumberOrPayerPhoneNumber(Long phn, Long phn2, Pageable pageable);

}
