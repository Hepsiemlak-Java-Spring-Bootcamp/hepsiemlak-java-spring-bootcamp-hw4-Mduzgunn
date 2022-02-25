package com.example.realestate.dto;

import com.example.realestate.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String baslik;
    private String icerigi;
    private User gonderici;
    private User alici;
}
