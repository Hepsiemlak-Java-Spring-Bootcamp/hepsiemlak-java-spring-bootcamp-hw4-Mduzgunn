package emlakburada.repository;

import emlakburada.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface BannerRepository extends JpaRepository<Banner,String> {
}
