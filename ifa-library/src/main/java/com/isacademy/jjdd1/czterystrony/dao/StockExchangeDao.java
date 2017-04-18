package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.util.List;

public interface StockExchangeDao<T extends FinancialInstrument> {

    T get(String id);

    List<T> getAllByName();
}