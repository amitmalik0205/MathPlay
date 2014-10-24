import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.GroupScoreDTO;


public class GroupScore {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/get-group-score";
		
		GroupScoreDTO dto = new GroupScoreDTO();
		dto.setGameName("Addition");
		dto.setGameClass("Class3");
		dto.setGroupID(56L);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
		
	}
}
