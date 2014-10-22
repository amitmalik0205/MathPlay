package com.qait.mathplay.rest;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.CreateGroupResponseDTO;
import com.qait.mathplay.service.IGroupMemberService;
import com.qait.mathplay.service.IGroupService;
import com.qait.mathplay.service.ISecurityQuestionService;
import com.qait.mathplay.service.IUserService;
import com.qait.mathplay.util.MathPlayNLearnUtil;
import com.qait.mathplay.util.MathPlayPropertiesFileReaderUtil;

@Component
@Path("math-play-service")
public class MathPlayService {
	
	private static final Logger logger = Logger.getLogger(MathPlayService.class);
	
	@Autowired
	@Qualifier("msgConfig")
	private Properties msgConfig;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IGroupMemberService memberService;
	
	@Autowired
	private ISecurityQuestionService questionService;
	
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String text() throws IOException {
		return "Its working";
	}
	
	@GET
	@Path("get-security-questions")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getSecurityQuestions() {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {

			return Response.ok(questionService.getAllQuestions()).build();

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getSecurityQuestion001");
			response.setMessage(msgConfig.getProperty("getSecurityQuestion001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
		}
		
		return Response.ok(response).build();
	}
	
	@POST
	@Path("register-new-user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response registerUser(User user) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {

			response.setCode("rsgisterUser001");
			response.setMessage(msgConfig.getProperty("rsgisterUser001"));

			if (userService.getUserByUserId(user.getUserID()) != null) {
				response.setCode("rsgisterUser003");
				response.setMessage(msgConfig.getProperty("rsgisterUser003"));
			} else {
				userService.saveUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("rsgisterUser002");
			response.setMessage(msgConfig.getProperty("rsgisterUser002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
		}

		return Response.ok(response).build();
	}
	
	@POST
	@Path("sign-in")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response authinticateUser(User user) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {
			if (userService.authenticateUser(user.getUserID(),
					user.getPassword()) != null) {
				response.setCode("signIn001");
				response.setMessage(msgConfig.getProperty("signIn001"));
				
			} else {
				response.setCode("signIn002");
				response.setMessage(msgConfig.getProperty("signIn002"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("signIn003");
			response.setMessage(msgConfig.getProperty("signIn003"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
		}
		return Response.status(200).entity(response).build();
	}
	
	/**
	 * Creates new group
	 * 
	 * @param userID
	 *            - Owner of the group
	 * @param groupName
	 *            - Name of the group
	 * @return
	 */
	@GET
	@Path("create-group")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response createGroup(@QueryParam("userID") String userID,
			@QueryParam("groupName") String groupName) {
		CreateGroupResponseDTO response = new CreateGroupResponseDTO();

		User user = userService.getUserByUserId(userID);
		if (user == null) {

			response.setCode("createGroup004");
			response.setMessage(MathPlayPropertiesFileReaderUtil
					.getPropertyValue("createGroup004"));

		} else {

			Group savedGroup = groupService.getGroupByGroupName(groupName);
			if (savedGroup != null) {

				response.setCode("createGroup003");
				response.setMessage(MathPlayPropertiesFileReaderUtil
						.getPropertyValue("createGroup003"));

			} else {

				Group newGroup = new Group();
				newGroup.setGroupName(groupName);
				newGroup.setGroupOwner(user);
				boolean isGroupSaved = groupService.saveGroup(newGroup);

				if (isGroupSaved) {

					// Add owner as group member
					Group createdGroup = groupService
							.getGroupByGroupName(groupName);
					GroupMember groupMember = new GroupMember(user,
							createdGroup, MemberStatus.ACCEPTED);
					memberService.saveMember(groupMember);

					response.setCode("createGroup001");
					response.setMessage(MathPlayPropertiesFileReaderUtil
							.getPropertyValue("createGroup001"));
					response.setGroupID(createdGroup.getGroupID());

				} else {

					response.setCode("createGroup002");
					response.setMessage(MathPlayPropertiesFileReaderUtil
							.getPropertyValue("createGroup002"));
				}
			}
		}
		return Response.status(200).entity(response).build();
	}
}
