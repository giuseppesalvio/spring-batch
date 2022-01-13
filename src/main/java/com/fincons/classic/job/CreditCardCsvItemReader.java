package com.fincons.classic.job;

import com.classic.domain.CreditCard;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class CreditCardCsvItemReader extends FlatFileItemReader<CreditCard> implements ItemReader<CreditCard> {

    public CreditCardCsvItemReader() {
        setResource(new FileSystemResource("inputData.csv"));
        //setLinesToSkip(1);
        setLineMapper(getDefaultLineMapper());
    }

    public DefaultLineMapper<CreditCard> getDefaultLineMapper() {
        DefaultLineMapper<CreditCard> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer =new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("id", "cardNumber", "lastPay");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        BeanWrapperFieldSetMapper<CreditCard> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(CreditCard.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        return defaultLineMapper;
    }
}
