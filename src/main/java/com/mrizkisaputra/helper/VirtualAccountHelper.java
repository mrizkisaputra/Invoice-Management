package com.mrizkisaputra.helper;

import com.mrizkisaputra.exceptions.VirtualAccountAlreadyPaidException;
import com.mrizkisaputra.exceptions.VirtualAccountNotFoundException;
import com.mrizkisaputra.models.entities.PaymentProvider;
import com.mrizkisaputra.models.entities.VirtualAccount;
import com.mrizkisaputra.repositories.PaymentRepository;
import com.mrizkisaputra.repositories.VirtualAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VirtualAccountHelper {
    private final VirtualAccountRepository virtualAccountRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public VirtualAccountHelper(VirtualAccountRepository virtualAccountRepository, PaymentRepository paymentRepository) {
        this.virtualAccountRepository = virtualAccountRepository;
        this.paymentRepository = paymentRepository;
    }

    public VirtualAccount getExistingVirtualAccount(
            PaymentProvider paymentProvider, String companyId, String accountNumber
    ) throws VirtualAccountNotFoundException {

        return virtualAccountRepository.findByPaymentProviderAndCompanyIdAndAccountNumber(paymentProvider, companyId, accountNumber)
                .orElseThrow(() -> new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + paymentProvider.getCode() + "] not found"));
    }

    public void checkVaAlreadyPaid(PaymentProvider paymentProvider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + paymentProvider.getCode() + "] already paid");
        }
    }

    public void checkPaymentAmount(VirtualAccount va, BigDecimal amount) {

    }
}
