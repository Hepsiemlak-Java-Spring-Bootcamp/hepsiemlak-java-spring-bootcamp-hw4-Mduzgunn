package com.example.realestate.service;

import com.example.realestate.dto.MessageRequest;
import com.example.realestate.dto.response.MeesageResponse;
import com.example.realestate.model.Message;
import com.example.realestate.model.User;
import com.example.realestate.repository.MessageRepository;
import com.example.realestate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    private MeesageResponse convertToMessageResponse(Message savedMessage) {
        MeesageResponse response = new MeesageResponse();
        response.setBaslik(savedMessage.getBaslik());
        response.setIcerigi(savedMessage.getIcerigi());
        response.setAlici(savedMessage.getAlici());
        response.setGonderici(savedMessage.getGonderici());
        return response;
    }

    private Message convertToMessage(MessageRequest request, Optional<User> foundUser) {
        Message message = null;
        if (foundUser.isPresent()) {
            message = new Message();
            message.setGonderici(foundUser.get());
            message.setBaslik(request.getBaslik());
            message.setIcerigi(request.getIcerigi());
        } else {
            System.out.println("Mesaj Bulunamadı!");
        }
        return message;
    }

    public List<MeesageResponse> getAllMessages() {
        List<MeesageResponse> messageList = new ArrayList<>();
        for (Message message :messageRepository.findAll()) {
            messageList.add(convertToMessageResponse(message));
        }
        return messageList;
    }

    public MeesageResponse getMessageById(int advertId) {
        Optional<Message> message = messageRepository.findById(advertId);
        return convertToMessageResponse(message.get());
    }
    public String deleteAdvertById(int id) {
        getMessageById(id);
        messageRepository.deleteById(id);
        return "message deleted successfully "+id;
    }
}
