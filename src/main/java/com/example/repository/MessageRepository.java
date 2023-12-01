package com.example.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface MessageRepository extends JpaRepository<Message, Integer>{
 @Query("SELECT m FROM Message m WHERE m.posted_by = :account_id")
 List<Message> getMessagesByAccountId(@Param("account_id") int account_id);

}
