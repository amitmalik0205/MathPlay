import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.LeaveGroupDTO;


public class GetUserPositionInAllGames {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/get-user-position-all-games";
		
		LeaveGroupDTO dto = new LeaveGroupDTO();
		dto.setGroupID(61L);
		dto.setUserKey(49L);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
	}


}
