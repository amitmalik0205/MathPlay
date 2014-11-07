import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.DeleteNotificationDTO;


public class DeleteNotifications {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/delete-notifications";
		
		DeleteNotificationDTO dto = new DeleteNotificationDTO();
		
		long arr[] = new long[2];
		arr[0] = 1L;
		arr[1] = 2L;
		
		List<Long> list = new ArrayList<Long>();
		list.add(1L);
		list.add(2L);
		
		dto.setNotificationIDArr(list);
		dto.setUserKey(49L);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
	}

}
