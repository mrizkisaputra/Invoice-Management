package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, String> {
}
