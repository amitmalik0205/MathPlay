import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ListGroupByOwner {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/group-list-for-owner?userID=amit1";

		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString("amit1");
		
		TestUtil.sendRequest(url, content, "GET");
		
	}
}
