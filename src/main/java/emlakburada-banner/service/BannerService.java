package emlakburada.service;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.request.UpdateBanerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Banner;
import emlakburada.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BannerService {

	@Autowired
	private BannerRepository repository;

	public List<BannerResponse> getAllBanners() {
		List<BannerResponse> bannerList = new ArrayList<>();
		for (Banner banner : repository.findAll()) {
			bannerList.add(convertToBannerResponse(banner));
		}
		return bannerList;
	}
	public BannerResponse saveBanner(BannerRequest request) {
		Banner banner = convertToBanner(request);
		try {
			if (banner == null) {
				throw new Exception("banner kaydedilemedi");
			}
			Banner saveBanner = repository.save(banner);
			return convertToBannerResponse(saveBanner);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public BannerResponse getBannerById(String bannerId) {
		Optional<Banner> banner = repository.findById(bannerId);
		return convertToBannerResponse(banner.get());
	}
	public String deleteBannerById(String id) {
		getBannerById(id);
		repository.deleteById(id);
		return "banner deleted successfully "+id;
	}

	public BannerResponse updateBanner(String id, UpdateBanerRequest updateBannerRequest) {
		BannerResponse banner = getBannerById(id);

		Banner updateBanner = new Banner(
				banner.getAdvertNo(),
				updateBannerRequest.getPhone(),
				banner.getTotal(),
				updateBannerRequest.getAddress()
		);
		return convertToBannerResponse(repository.save(updateBanner));
	}


	private BannerResponse convertToBannerResponse(Banner banner) {
		BannerResponse response = new BannerResponse();
		response.setAdvertNo(banner.getAdvertNo());
		response.setPhone(banner.getPhone());
		response.setTotal(banner.getTotal());
		return response;
	}

	private Banner convertToBanner(BannerRequest request) {
		Banner banner = new Banner();
		banner.setAdvertNo(request.getAdvertNo());
		banner.setPhone(request.getPhone());
		banner.setTotal(request.getTotal());
		return banner;
	}

}
