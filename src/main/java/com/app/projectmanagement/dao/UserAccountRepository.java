package com.app.projectmanagement.dao;


import com.app.projectmanagement.entities.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{
}
