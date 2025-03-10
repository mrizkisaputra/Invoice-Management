package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.VirtualAccount;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualAccountRepository extends ListPagingAndSortingRepository<VirtualAccount, String> {
}
