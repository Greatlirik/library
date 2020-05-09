package com.github.greatlirik.library.repository;

import com.github.greatlirik.library.entity.AccountEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AccountRepository extends PagingAndSortingRepository<AccountEntity,Long> {
    Optional<AccountEntity> findByName(String username);
}
