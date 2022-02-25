package com.example.realestate.dto.response;

import com.example.realestate.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class MeesageResponse {
    private String baslik;
    private String icerigi;
    private Date gonderilenTarih;
    private Date okunduguTarihi;
    private boolean gorulduMu;
    private User gonderici;
    private User alici;

}
