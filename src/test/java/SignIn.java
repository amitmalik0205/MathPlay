import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dao.domain.User;


public class SignIn {
	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/sign-in";
	
		User user = new User();
		user.setUserID("amit0205");
		user.setAnswer("Answer");
		user.setPassword("1234");

		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(user);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
		
	}
}
