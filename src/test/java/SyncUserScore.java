import java.io.IOException;

import javax.xml.bind.JAXBException;


public class SyncUserScore {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/sync-user-scores/50";
		
		TestUtil.sendRequest(url, "", "GET");
		
	}
}
