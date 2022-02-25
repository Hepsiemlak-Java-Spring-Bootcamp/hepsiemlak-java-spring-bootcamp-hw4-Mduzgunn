package com.example.realestate.service;

import com.example.realestate.dto.AdvertRequest;
import com.example.realestate.dto.UpdateAdvertRequest;
import com.example.realestate.dto.response.AdvertResponse;
import com.example.realestate.model.Advert;
import com.example.realestate.model.User;
import com.example.realestate.repository.AdvertRepository;
import com.example.realestate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    public AdvertService(AdvertRepository advertRepository, UserRepository userRepository) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }

    private AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
        AdvertResponse response = new AdvertResponse();
        response.setBaslik(savedAdvert.getBaslik());
        response.setFiyat(savedAdvert.getFiyat());
        response.setAdvertNo(savedAdvert.getId());
        response.setKullanici(savedAdvert.getCreatorUser());
        return response;
    }

    private Advert convertToAdvert(AdvertRequest request, Optional<User> foundUser) {
        Advert advert = null;
        if (foundUser.isPresent()) {
            advert = new Advert();
            advert.setCreatorUser(foundUser.get());
            advert.setBaslik(request.getBaslik());
            advert.setFiyat(request.getFiyat());
        } else {
            System.out.println("Kullanıcı Bulunamadı!");
        }
        return advert;
    }

    public List<AdvertResponse> getAllAdverts() {
        List<AdvertResponse> advertList = new ArrayList<>();
        for (Advert advert : advertRepository.findAll()) {
            advertList.add(convertToAdvertResponse(advert));
        }
        return advertList;
    }

    public AdvertResponse saveAdvert(AdvertRequest request) {
        Optional<User> foundUser = userRepository.findById(request.getUserId());
        Advert advert = convertToAdvert(request, foundUser);
        try {
            if (advert == null) {
                throw new Exception("İlan kaydedilemedi");
            }
            Advert savedAdvert = advertRepository.save(advert);
            return convertToAdvertResponse(savedAdvert);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public AdvertResponse updateAdvert(int id, UpdateAdvertRequest updateAdvertRequest) {
        AdvertResponse advert = getAdvertByAdvertId(id);
        Advert updateAdvert = new Advert(
                advert.getAdvertNo(),
                updateAdvertRequest.getBaslik(),
                advert.getKullanici(),
                advert.getResimList(),
                updateAdvertRequest.getFiyat(),
                advert.getSuresi(),
                advert.isOneCikarilsinMi(),
                advert.isIncelendiMi(),
                advert.getOlusturulmaTarihi(),
                updateAdvertRequest.isAktifMi()
        );
        return convertToAdvertResponse(advertRepository.save(updateAdvert));
    }

    public AdvertResponse getAdvertByAdvertId(int advertId) {
        Optional<Advert> advert = advertRepository.findById(advertId);
        return convertToAdvertResponse(advert.get());
    }
    public String deleteAdvertById(int id) {
        getAdvertByAdvertId(id);
        advertRepository.deleteById(id);
        return "advert deleted successfully "+id;
    }

}
