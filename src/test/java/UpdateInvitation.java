import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dto.UpdateInvitationStatusDTO;


public class UpdateInvitation {

public static void main(String[] args) throws JAXBException, IOException {
		
		String url = "http://localhost:8081/MathPlay/rest/math-play-service/update-invitation-status";
		
		UpdateInvitationStatusDTO dto = new UpdateInvitationStatusDTO();
		dto.setGroupID(57L);
		dto.setMemberID(54L);
		dto.setStatus(MemberStatus.ACCEPTED);
		
		ObjectMapper mapper = new ObjectMapper();
		String content = mapper.writeValueAsString(dto);
		System.out.println(content);

		TestUtil.sendRequest(url, content, "POST");
	}
}
