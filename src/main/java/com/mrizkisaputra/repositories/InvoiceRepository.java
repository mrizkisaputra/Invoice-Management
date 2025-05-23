package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
