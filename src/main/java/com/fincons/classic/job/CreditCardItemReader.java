package com.fincons.classic.job;

import com.fincons.classic.domain.CreditCard;
import com.fincons.classic.repository.CreditCardRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class CreditCardItemReader implements ItemReader<CreditCard> {

    private Iterator<CreditCard> usersIterator;
    private final CreditCardRepository respository;

    public CreditCardItemReader(CreditCardRepository respository) {
        this.respository = respository;
    }

    @BeforeStep
    public void before(StepExecution stepExecution) {
        usersIterator = respository.findAll().iterator();
    }

    @Override
    public CreditCard read() {
        if (usersIterator != null && usersIterator.hasNext()) {
            return usersIterator.next();
        } else {
            return null;
        }
    }
}
