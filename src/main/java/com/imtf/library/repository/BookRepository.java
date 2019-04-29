package com.imtf.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.imtf.library.entity.BookMaster;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<BookMaster, Long> {
	
	@Transactional
	@Query(value = "SELECT count(*) FROM BookMaster bm WHERE bm.bookName = :bookName")
	int checkBookNameExists(@Param("bookName") String bookName);

	@Transactional
	@Query(value = "SELECT count(*) FROM BookMaster bm WHERE bm.bookName = :bookName AND bm.bookId != :bookId")
	int checkBookNameExistsEdit(@Param("bookName") String bookName, @Param("bookId") long bookId);
	
	@Transactional
	@Query(value = "SELECT bm FROM BookMaster bm WHERE bm.bookId = :bookId")
	BookMaster selectBookDetails(@Param("bookId") long bookId);
	
	@Transactional
	@Query(value = "SELECT bm FROM BookMaster bm WHERE bm.category = :categoryId")
	List<BookMaster> selectBooksByCategory(@Param("categoryId") int categoryId);

}
