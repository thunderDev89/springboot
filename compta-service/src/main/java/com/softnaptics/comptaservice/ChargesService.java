package com.softnaptics.comptaservice;

import com.softnaptics.comptarepository.ChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargesService {

    private final ChargesRepository repository;

    @Autowired
    public ChargesService(ChargesRepository repository) {
        this.repository = repository;
    }
}
