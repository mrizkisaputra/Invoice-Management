package com.mrizkisaputra.services;

import com.mrizkisaputra.exceptions.PaymentExceedInvoiceAmountException;
import com.mrizkisaputra.exceptions.VirtualAccountAlreadyPaidException;
import com.mrizkisaputra.exceptions.VirtualAccountNotFoundException;
import com.mrizkisaputra.helper.VirtualAccountHelper;
import com.mrizkisaputra.models.entities.Invoice;
import com.mrizkisaputra.models.entities.PaymentProvider;
import com.mrizkisaputra.models.entities.VirtualAccount;
import com.mrizkisaputra.repositories.PaymentRepository;
import com.mrizkisaputra.repositories.VirtualAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private VirtualAccountRepository virtualAccountRepository;
    private AuditLogService auditLogService;
    private VirtualAccountHelper virtualAccountHelper;


    @Override
    @Transactional(
            rollbackFor = {
                    VirtualAccountNotFoundException.class,
                    PaymentExceedInvoiceAmountException.class
            }
    )
    public void pay(PaymentProvider paymentProvider,
                    String companyId, String accountNumber,
                    BigDecimal amount, String reference
    ) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException {

        auditLogService.log("Start payment VA["+accountNumber+"]");

        VirtualAccount va = virtualAccountHelper.getExistingVirtualAccount(paymentProvider, companyId, accountNumber);
        virtualAccountHelper.checkVaAlreadyPaid(paymentProvider, companyId, accountNumber, va);



        // 3. cek apakah total akumulasi pembayaran < amount

        // 4. update status VA menjadi lunas

        // 5. update status invoice menjadi lunas

        // 6. insert ke tabel payment

        // 7. notifikasi (next fase)
    }



    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    public void setVirtualAccountRepository(VirtualAccountRepository virtualAccountRepository) {
        this.virtualAccountRepository = virtualAccountRepository;
    }

    @Autowired
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Autowired
    public void setVirtualAccountHelper(VirtualAccountHelper virtualAccountHelper) {
        this.virtualAccountHelper = virtualAccountHelper;
    }
}
