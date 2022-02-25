package emlakburada.controller;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.request.UpdateBanerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
public class BannerController {

	private final BannerService service;

	public BannerController(BannerService service) {
		this.service = service;
	}

	@GetMapping(value = "/banners")
	public ResponseEntity<List<BannerResponse>> getAllBanners() {
		return new ResponseEntity<>(service.getAllBanners(), HttpStatus.OK);
	}

	@PostMapping(value = "/banners")
	public ResponseEntity<BannerResponse> createBanner(@RequestBody BannerRequest request) {
		return new ResponseEntity<>(service.saveBanner(request), HttpStatus.OK);
	}

	@GetMapping(value = "/banners/{bannerNo}")
	public ResponseEntity<BannerResponse> getBannerById(@PathVariable(required = false) String bannerNo) {
		return new ResponseEntity<>(service.getBannerById(bannerNo), HttpStatus.OK);
	}

	@PutMapping(value = "/banners/{id}")
	public ResponseEntity<BannerResponse> updateBanner(@PathVariable String id,@Valid @RequestBody UpdateBanerRequest updateBanerRequest) {
		return ResponseEntity.ok(service.updateBanner(id, updateBanerRequest));
	}

	@DeleteMapping("/banners/{id}")
	public ResponseEntity<String> deleteAdvertByID(@PathVariable String id){
		return ResponseEntity.ok(service.deleteBannerById(id));
	}

}
