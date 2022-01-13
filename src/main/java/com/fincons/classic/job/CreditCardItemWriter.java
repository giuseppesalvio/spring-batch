package com.fincons.classic.job;

import com.fincons.classic.domain.CreditCardRisk;
import com.fincons.classic.repository.CreditCardRiskRespository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditCardItemWriter implements ItemWriter<CreditCardRisk> {

    private final CreditCardRiskRespository creditCardRiskRespository;

    public CreditCardItemWriter(CreditCardRiskRespository creditCardRiskRespository) {
        this.creditCardRiskRespository = creditCardRiskRespository;
    }

    @Override
    public void write(List<? extends CreditCardRisk> list) throws Exception {
        creditCardRiskRespository.saveAll(list);
    }
}
