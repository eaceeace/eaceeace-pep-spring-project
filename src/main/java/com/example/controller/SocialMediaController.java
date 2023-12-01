package com.example.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService; 
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping
public class SocialMediaController {
    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerHandler(@RequestBody Account user) {
        if (user.getPassword().length() >= 4 && !user.getUsername().isEmpty()) {Account record= accountService.findAccountByUsername(user.getUsername());
            if (record== null) { Account reg = accountService.register(user);
            if (reg != null) {return ResponseEntity.status(200).body(reg);}
            else {return ResponseEntity.status(400).body(null);}}  
            else {return ResponseEntity.status(409).body(null);}}
            else {return ResponseEntity.status(400).body(null);}}
    @PostMapping("/login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account user){
        Account log= accountService.login(user);
        if(log!=null) {return ResponseEntity.status(200).body(log);}
        else {return ResponseEntity.status(401).body(log);}
    }
    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessageHandler(){
        List<Message> msg= messageService.getMessage();
        return ResponseEntity.status(200).body(msg);
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message message){
        Message create = messageService.createMessage(message);
        if (create !=null) {
            return ResponseEntity.status(200).body(create);}
        else {return ResponseEntity.status(400).body(create);}
    }
    @DeleteMapping("/messages/{message_id}")
    public @ResponseBody ResponseEntity<Integer> deleteMessageByIdHandler(@PathVariable int message_id){
    Message del = messageService.deleteMessageByIdHandler(message_id);
    if (del!=null){
        String[] msg = del.getMessage_text().split("\r|\n");
        return ResponseEntity.status(200).body(msg.length);}
    else{return ResponseEntity.status(200).body(0);}
    }
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") int message_id){
    Message msg = messageService.getMessageById(message_id);
    return ResponseEntity.status(200).body(msg);
    }
    @GetMapping("/accounts/{account_id}/messages")
    public @ResponseBody ResponseEntity<List<Message>>  getMessagesByUser(@PathVariable int account_id){
    List<Message> msg= messageService.getMessagesByUser(account_id);
    return ResponseEntity.status(200).body(msg);}
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Message> updateMessageHandler(@PathVariable("message_id") int message_id, @RequestBody Message message) {
        Optional<Message> update= messageService.updateMessage(message_id, message);
        if (update.isPresent()) {return ResponseEntity.status(200).body(update.get());}
        else {return ResponseEntity.status(400).body(null);}}
    

}