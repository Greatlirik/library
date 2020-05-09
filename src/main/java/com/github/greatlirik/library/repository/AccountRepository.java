package com.github.greatlirik.library.repository;

import com.github.greatlirik.library.entity.AccountEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<AccountEntity,Long> {
    AccountEntity findByName(String username);
}
