package com.mrizkisaputra;

import com.mrizkisaputra.models.entities.Invoice;
import com.mrizkisaputra.models.entities.InvoiceType;
import com.mrizkisaputra.repositories.InvoiceTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/delete-invoice-type.sql",
"classpath:/sql/insert-invoice-type.sql"})
public class InvoiceTypeRepositoryTest {

    @Autowired
    InvoiceTypeRepository invoiceTypeRepository;

    @Test
    void testAuditTrail() throws InterruptedException {
        InvoiceType invoiceType = new InvoiceType();
        invoiceType.setCode("IT-001");
        invoiceType.setName("Invoice Type Test");
        invoiceTypeRepository.save(invoiceType);

        Assertions.assertNotNull(invoiceType.getId());
        Assertions.assertNotNull(invoiceType.getLastCreatedAt());
        Assertions.assertNotNull(invoiceType.getLastUpdatedAt());
        Assertions.assertNotNull(invoiceType.getLastCreatedBy());
        Assertions.assertNotNull(invoiceType.getLastUpdatedBy());
        Assertions.assertEquals(invoiceType.getLastCreatedAt(), invoiceType.getLastUpdatedAt());

        Thread.sleep(2000);

        // update
        invoiceType.setName("Invoice Type Test Update");
        var it = invoiceTypeRepository.save(invoiceType);
        Assertions.assertNotEquals(it.getLastCreatedAt(), it.getLastUpdatedAt());
    }

    @Test
    void testQuerySoftDelete() {
        long totalRecord = invoiceTypeRepository.count();
        Assertions.assertEquals(1, totalRecord);
    }

    @Test
    void testSoftDelete() {
        InvoiceType it = invoiceTypeRepository.findById("IT-002").orElse(null);
        invoiceTypeRepository.delete(it);
    }

}
