package com.mrizkisaputra.services;

import com.mrizkisaputra.exceptions.PaymentExceedInvoiceAmountException;
import com.mrizkisaputra.exceptions.VirtualAccountAlreadyPaidException;
import com.mrizkisaputra.exceptions.VirtualAccountNotFoundException;
import com.mrizkisaputra.models.entities.PaymentProvider;

import java.math.BigDecimal;

public interface PaymentService {

    public void pay(PaymentProvider paymentProvider,
                    String companyId, String accountNumber,
                    BigDecimal amount, String reference
    ) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException;
}
