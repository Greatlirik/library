package com.github.greatlirik.library.repository;

import com.github.greatlirik.library.entity.AuthorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, Integer> {
}
