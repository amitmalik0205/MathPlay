import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dao.domain.SecurityQuestion;
import com.qait.mathplay.dao.domain.User;


public class CreateUser {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/register-new-user";
		
		SecurityQuestion question = new SecurityQuestion();
		question.setQuestionId(4L);
		
		User user = new User();
		user.setUserID("amit12");
		user.setAnswer("Answer");
		user.setPassword("1234");
		user.setCity("Delhi");
		user.setCountry("India");
		user.setName("Amit");
		user.setSecurityQuestion(question);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(user);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
		
	}


}
