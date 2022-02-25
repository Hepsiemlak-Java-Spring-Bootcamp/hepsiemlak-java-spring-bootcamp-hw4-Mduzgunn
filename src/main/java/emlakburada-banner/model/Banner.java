package emlakburada.model;

import emlakburada.dto.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {

	private int advertNo;
	private String phone;
	private int total;
	private AddressRequest address;

}
