import java.io.IOException;

import javax.xml.bind.JAXBException;


public class ListGroupForMember {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/group-list-for-member/amit12";

		TestUtil.sendRequest(url, "", "GET");
	}
}
