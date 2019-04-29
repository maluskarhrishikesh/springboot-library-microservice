package com.imtf.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.imtf.library.entity.UserMaster;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <UserMaster, Long> {
	
	@Transactional
	@Query(value = "SELECT count(*) FROM UserMaster um WHERE um.userName = :userName")
	int checkUserNameExists(@Param("userName") String userName);
	
	@Transactional
	@Query(value = "SELECT um FROM UserMaster um WHERE um.userId = :userId")
	int selectUserDetails(@Param("userId") long userId);

}
