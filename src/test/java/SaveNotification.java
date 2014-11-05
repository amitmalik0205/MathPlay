import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.GameDetailsDTO;


public class SaveNotification {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/save-high-score-notification";
		
		/*GameDetailsDTO dto = new GameDetailsDTO();
		dto.setGameClass("Class3");
		dto.setGameName("Addition");
		dto.setLevel("medium");
		dto.setUserScore(600L);
		dto.setUserID("amit1");
		dto.setGameID(14L);
		dto.setCity("Mumbai");
		dto.setCountry("India");
		dto.setName("Amit");
		dto.setUserKey(50L);*/
		
		GameDetailsDTO dto = new GameDetailsDTO();
		dto.setGameClass("Class3");
		dto.setGameName("Divison");
		dto.setLevel("medium");
		dto.setUserScore(600L);
		dto.setUserID("amit1");
		dto.setGameID(14L);
		dto.setCity("Mumbai");
		dto.setCountry("India");
		dto.setName("Amit");
		dto.setUserKey(50L);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
	}
}
