import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetInvitations {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/get-invitations/amit12";
		
		TestUtil.sendRequest(url, "", "GET");
	}
}
