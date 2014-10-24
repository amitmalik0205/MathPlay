import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.GameDetailsDTO;


public class SaveUserScore {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/save-game-score";
		
		GameDetailsDTO dto = new GameDetailsDTO();
		dto.setGameClass("Class3");
		dto.setGameName("Addition");
		dto.setLevel("medium");
		dto.setUserScore(800L);
		dto.setUserID("amit1");
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
	}


}
