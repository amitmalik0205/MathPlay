import java.io.IOException;

import javax.xml.bind.JAXBException;

public class FriendList {
	
	public static void main(String[] args) throws JAXBException, IOException {

		String url = "http://localhost:8081/MathPlay/rest/math-play-service/friend-list/amit1";

		TestUtil.sendRequest(url, null, "GET");

	}
}
