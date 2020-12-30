package it.unical.asd.group6.computerSparePartsCompany.core.services;

import it.unical.asd.group6.computerSparePartsCompany.data.entities.Purchase;

import java.util.List;

public interface PurchaseService {
    Boolean registerNewPurchase(Purchase purchase);

    List<Purchase> getAllPurchases();
}
