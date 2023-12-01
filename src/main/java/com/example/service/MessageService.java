package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Message> getMessage(){
    return messageRepository.findAll();
    }
    public Message getMessageById(int message_id){
        Optional<Message> message = messageRepository.findById(message_id);
        if (message.isPresent()){return message.get();}
        else{return null;}}
    public List<Message> getMessagesByUser(int account_id) {
    List<Message> msg = messageRepository.findAll();
    List<Message> UpdatedMessage = msg.stream()
            .filter(message -> message.getPosted_by() == account_id)
            .collect(Collectors.toList());
    return UpdatedMessage;}
    public Message deleteMessageByIdHandler(int message_id){
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()){
            Message del = optionalMessage.get();
            messageRepository.deleteById(message_id);
            return del;
        }else{return null; }}
    public Message createMessage(Message message){
        Optional<Account> create = accountRepository.findById(message.getPosted_by());
        if (message.getMessage_text() !=null && create.isPresent()){
            return messageRepository.save(message);
        } else{ return null;}}
        public Optional<Message> updateMessage(int message_id, Message message) {
            Optional<Message> msg = messageRepository.findById(message_id);
            if (msg.isPresent() && message.getMessage_text().length() < 255) {
                Message update = msg.get();
                update.setMessage_text(message.getMessage_text());
                messageRepository.save(update);
                return Optional.of(update);}
            else {return Optional.empty();}}
            
        
        

}
