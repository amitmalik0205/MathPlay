import java.io.IOException;

import javax.xml.bind.JAXBException;


public class GetSecurityQuestions {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/get-security-questions";

		TestUtil.sendRequest(url, "", "GET");
		
	}

}
