package com.github.greatlirik.library.repository;

import com.github.greatlirik.library.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, Integer> {
    Iterable<BookEntity> findAllByTitle(String title);
//    Iterable<BookEntity> findAllByAccount(String account);
}
