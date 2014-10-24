import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathplay.dto.GroupMemberDTO;


public class AddMember {

	public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/add-member-to-group";
		
		GroupMemberDTO memberDTO = new GroupMemberDTO();
		memberDTO.setGroupID(56);
		memberDTO.setUserKey(54);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(memberDTO);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
		
	}
}
