package com.example.demo.Repositories;

import com.example.demo.Classes.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet,Long> {

    public Wallet findByPhoneNumber(Long phn);

    public Wallet findByUserId(Long id);
}
