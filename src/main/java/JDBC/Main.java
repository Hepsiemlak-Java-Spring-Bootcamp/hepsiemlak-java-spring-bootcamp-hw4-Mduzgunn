package JDBC;

import JDBC.dao.AdvertRepository;
import JDBC.dao.UserRepository;
import JDBC.entity.Advert;
import JDBC.entity.User;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		UserRepository userRepository = new UserRepository();
		userRepository.save(prepareUser(1, "cem"));
		userRepository.save(prepareUser(2, "emir"));
		userRepository.save(prepareUser(3, "nehir"));
		userRepository.save(prepareUser(4, "melike"));
		userRepository.save(prepareUser(5, "serra"));
		System.out.println("--find all user--");

		List<User> users = userRepository.findAll();

		users.stream().forEach(user -> System.out.println(user.toString()));

		System.out.println("--find user--");

		User foundUser = userRepository.findOne(2);

		System.out.println(foundUser.toString());


		AdvertRepository advertRepository = new AdvertRepository();
		advertRepository.save(prepareAdvert(1, "baslik1",userRepository.findOne(1),121,true));
		advertRepository.save(prepareAdvert(2, "baslik2",userRepository.findOne(2),333,false));
		advertRepository.save(prepareAdvert(3, "baslik3",userRepository.findOne(3),221,true));
		System.out.println("--find all adverts--");

		List<Advert> adverts = advertRepository.findAll();

		adverts.stream().forEach(advert -> System.out.println(advert.toString()));

		System.out.println("--find advert--");

		Advert foundAdvert = advertRepository.findOne(2);

		System.out.println(foundAdvert.toString());

	}

	private static User prepareUser(int id, String name) {
		User user = new User();
		user.id = id;
		user.name = name;
		user.email = "cemdrman@gmail.com";
		user.bio = "bio";
		return user;
	}

	private static Advert prepareAdvert(int id, String baslik, User creator, Integer fiyat, Boolean aktif) {
		Advert advert = new Advert();
		advert.id = id;
		advert.baslik = baslik;
		advert.creator = creator;
		advert.fiyat = fiyat;
		advert.aktif = aktif;
		return advert;
	}

}
