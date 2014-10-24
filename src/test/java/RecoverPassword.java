import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.RecoverPasswordDTO;


public class RecoverPassword {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/recover-password";
		
		RecoverPasswordDTO dto = new RecoverPasswordDTO();
		dto.setUserID("amit1");
		dto.setAnswer("");
		dto.setQuestionID(4L);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
	}
}
