package com.github.greatlirik.library.repository;

import com.github.greatlirik.library.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, Integer> {
    List<BookEntity> findAllByTitleContainingIgnoreCase(String title);
}
