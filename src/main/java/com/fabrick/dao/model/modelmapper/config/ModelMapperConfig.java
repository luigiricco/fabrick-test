package com.fabrick.dao.model.modelmapper.config;

import com.fabrick.dao.model.FabrickTransaction;
import com.fabrick.rs.dto.FabrickTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@Slf4j
public class ModelMapperConfig {
    @Value("${date-pattern:yyyy-MM-dd}")
    private String datePattern = "yyyy-MM-dd";

    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

    Converter<String, Date> string2DateConverter = mappingContext -> {
        if (mappingContext.getSource() != null) {

            try {
                return sdf.parse(mappingContext.getSource());
            } catch (ParseException e) {
                log.warn("Problem converting from string 2 date", e);
            }
        }

        return null;
    };

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mp = new ModelMapper();

        PropertyMap<FabrickTransactionDto, FabrickTransaction> transactionPropertyMap = new PropertyMap<FabrickTransactionDto, FabrickTransaction>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        };

        mp.addMappings(transactionPropertyMap);

        this.mappingFabrickTransactionDto2FabrickTransaction(mp);

        return mp;
    }

    private void mappingFabrickTransactionDto2FabrickTransaction(ModelMapper mp) {
        mp.typeMap(FabrickTransactionDto.class, FabrickTransaction.class)
                .addMappings(mapper -> mapper.using(string2DateConverter).map(FabrickTransactionDto::getAccountingDate, FabrickTransaction::setAccountingDate))
                .addMappings(mapper -> mapper.using(string2DateConverter).map(FabrickTransactionDto::getValueDate, FabrickTransaction::setValueDate));
    }

}
