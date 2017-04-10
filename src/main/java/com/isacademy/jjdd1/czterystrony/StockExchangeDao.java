package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public interface StockExchangeDao<T extends FinancialInstrument> {
    void add(T instrument);

    T get(String instrumentName);

    List<T> getAllByName();
}