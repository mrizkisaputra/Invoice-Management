package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.PaymentProvider;
import com.mrizkisaputra.models.entities.VirtualAccount;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VirtualAccountRepository extends ListPagingAndSortingRepository<VirtualAccount, String> {
    /**
     * select * from virtual_account where id_payment_provider = ? and company_id = ? and account_number = ?
     */
    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider paymentProvider,
                                                                               String companyId, String accountNumber);
}
