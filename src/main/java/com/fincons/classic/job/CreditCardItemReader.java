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

    private Iterator<CreditCard> creditCardIterator;
    private final CreditCardRepository creditCardRepository;

    public CreditCardItemReader(CreditCardRepository respository) {
        this.creditCardRepository = respository;
    }

    @BeforeStep
    public void before(StepExecution stepExecution) {
        creditCardIterator = creditCardRepository.findAll().iterator();
    }

    @Override
    public CreditCard read() {
        if (creditCardIterator != null && creditCardIterator.hasNext()) {
            return creditCardIterator.next();
        } else {
            return null;
        }
    }
}
