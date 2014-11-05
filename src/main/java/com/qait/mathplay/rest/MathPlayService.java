package com.qait.mathplay.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qait.mathlay.enums.MemberStatus;
import com.qait.mathplay.dao.domain.Game;
import com.qait.mathplay.dao.domain.GameDetails;
import com.qait.mathplay.dao.domain.Group;
import com.qait.mathplay.dao.domain.GroupMember;
import com.qait.mathplay.dao.domain.Notification;
import com.qait.mathplay.dao.domain.SecurityQuestion;
import com.qait.mathplay.dao.domain.User;
import com.qait.mathplay.dto.CreateGroupResponseDTO;
import com.qait.mathplay.dto.GameDetailsDTO;
import com.qait.mathplay.dto.GetInvitationsDTO;
import com.qait.mathplay.dto.GroupDTO;
import com.qait.mathplay.dto.GroupMemberDTO;
import com.qait.mathplay.dto.GroupMemberInfoDTO;
import com.qait.mathplay.dto.GroupScoreDTO;
import com.qait.mathplay.dto.LeaveGroupDTO;
import com.qait.mathplay.dto.NotificationDTO;
import com.qait.mathplay.dto.RecoverPasswordDTO;
import com.qait.mathplay.dto.UpdateInvitationStatusDTO;
import com.qait.mathplay.dto.UserDTO;
import com.qait.mathplay.dto.UserGameScoreDTO;
import com.qait.mathplay.dto.UserRankResponseDTO;
import com.qait.mathplay.service.IGameDetailsService;
import com.qait.mathplay.service.IGameService;
import com.qait.mathplay.service.IGroupMemberService;
import com.qait.mathplay.service.IGroupService;
import com.qait.mathplay.service.INotificationService;
import com.qait.mathplay.service.ISecurityQuestionService;
import com.qait.mathplay.service.IUserService;
import com.qait.mathplay.util.MathPlayNLearnUtil;

@Service
@Path("math-play-service")
public class MathPlayService {

	private static final Logger logger = Logger
			.getLogger(MathPlayService.class);

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

	@Autowired
	private IGameService gameService;

	@Autowired
	private IGameDetailsService detailsService;
	
	@Autowired
	private INotificationService notificationService;

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
		List<SecurityQuestion> list = null;
		try {

			list = questionService.getAllQuestions();

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getSecurityQuestion001");
			response.setMessage(msgConfig.getProperty("getSecurityQuestion001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(list).build();
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
			throw new WebApplicationException(Response.ok(response).build());
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
			User savedUser = userService.authenticateUser(user.getUserID(),
					user.getPassword());
			if (savedUser != null) {
				UserDTO dto = new UserDTO();
				dto.setUserKey(savedUser.getId());
				dto.setUserID(savedUser.getUserID());
				dto.setName(savedUser.getName());
				dto.setCity(savedUser.getCity());
				dto.setCountry(savedUser.getCountry());

				return Response.ok(dto).build();

			} else {
				response.setCode("signIn002");
				response.setMessage(msgConfig.getProperty("signIn002"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("signIn003");
			response.setMessage(msgConfig.getProperty("signIn003"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	/**
	 * Service will return the List of UseID's matching with @param searchStr
	 * 
	 * @param searchStr
	 *            - Search String
	 * @return - List of UserID's
	 */
	@GET
	@Path("search-user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response searchUser(
			@Length(min = 1, message = "{SearchUser.searchStr.empty}") @QueryParam("userIDString") String searchStr,
			@NotNull(message = "{SearchUser.groupid.empty}") @QueryParam("groupID") long groupID) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		List<GroupMemberInfoDTO> fullList = null;

		try {

			List<GroupMemberInfoDTO> groupList = userService
					.getMatchingUserIDForGroup(searchStr, groupID);

			fullList = new CopyOnWriteArrayList<GroupMemberInfoDTO>(
					userService.getMatchingUserID(searchStr));

			for (GroupMemberInfoDTO outer : groupList) {
				for (GroupMemberInfoDTO inner : fullList) {
					if (outer.getUserKey() == inner.getUserKey()) {
						fullList.remove(inner);
						break;
					}
				}
			}
			groupList.addAll(fullList);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("searchUser001");
			response.setMessage(msgConfig.getProperty("searchUser001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(fullList).build();
	}

	@POST
	@Path("recover-password")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response recoverPassword(@Valid RecoverPasswordDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {

			User savedUser = userService.getUserWithSecurityQuestion(dto
					.getUserID());

			if (savedUser == null) {
				response.setCode("recoverPassword002");
				response.setMessage(msgConfig.getProperty("recoverPassword002"));

			} else {

				SecurityQuestion savedQuestion = savedUser
						.getSecurityQuestion();
				Long questionID = dto.getQuestionID();

				if (questionID.equals(savedQuestion.getQuestionId())) {
					if (savedUser
							.getAnswer()
							.replaceAll("\\s", "")
							.equalsIgnoreCase(
									dto.getAnswer().replaceAll("\\s", ""))) {

						response.setCode("recoverPassword001");
						response.setMessage(msgConfig
								.getProperty("recoverPassword001")
								+ " "
								+ savedUser.getPassword());
					} else {
						response.setCode("recoverPassword004");
						response.setMessage(msgConfig
								.getProperty("recoverPassword004"));
					}

				} else {
					response.setCode("recoverPassword003");
					response.setMessage(msgConfig
							.getProperty("recoverPassword003"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("recoverPassword005");
			response.setMessage(msgConfig.getProperty("recoverPassword005"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("save-game-score")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response saveUserGameScore(@Valid GameDetailsDTO details) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {
			response.setCode("saveUserScore001");
			response.setMessage(msgConfig.getProperty("saveUserScore001"));

			User savedUser = userService.getUserByUserId(details.getUserID());

			if (savedUser == null) {
				response.setCode("saveUserScore003");
				response.setMessage(msgConfig.getProperty("saveUserScore003"));

			} else {

				String gameName = details.getGameName();
				String gameClass = details.getGameClass();
				Game savedGame = gameService.getGameByNameAndClass(gameName,
						gameClass);

				if (savedGame == null) {
					Game newGame = new Game();
					newGame.setGameClass(gameClass);
					newGame.setGameName(gameName);

					gameService.saveGame(newGame);
				}

				savedGame = gameService.getGameByNameAndClass(gameName,
						gameClass);

				GameDetails savedGameDetails = detailsService
						.getGameDetailsByUserAndGame(savedUser.getId(),
								savedGame.getGameId());

				if (savedGameDetails == null) {
					GameDetails gameDetails = new GameDetails();
					gameDetails.setGame(savedGame);
					gameDetails.setUser(savedUser);
					gameDetails.setLevel(details.getLevel());
					gameDetails.setUserScore(details.getUserScore());
					detailsService.saveGameDetails(gameDetails);
				} else {
					savedGameDetails.setLevel(details.getLevel());
					savedGameDetails.setUserScore(details.getUserScore());
					detailsService.saveGameDetails(savedGameDetails);
				}

				details.setGameID(savedGame.getGameId());
				details.setUserKey(savedUser.getId());
				details.setName(savedUser.getName());
				details.setCity(savedUser.getCity());
				details.setCountry(savedUser.getCountry());

				return Response.ok(details).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("saveUserScore002");
			response.setMessage(msgConfig.getProperty("saveUserScore002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@GET
	@Path("create-group")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response createGroup(@QueryParam("userID") String ownerID,
			@QueryParam("groupName") String groupName) {
		CreateGroupResponseDTO response = new CreateGroupResponseDTO();
		try {
			User user = userService.getUserByUserId(ownerID);
			if (user == null) {
				response.setCode("createGroup004");
				response.setMessage(msgConfig.getProperty("createGroup004"));

			} else {

				Group savedGroup = groupService.getGroupByGroupName(groupName);
				if (savedGroup != null) {
					response.setCode("createGroup003");
					response.setMessage(msgConfig.getProperty("createGroup003"));
				} else {

					Group newGroup = new Group();
					newGroup.setGroupName(groupName);
					newGroup.setGroupOwner(user);

					groupService.saveGroup(newGroup);

					// Add owner as group member
					Group createdGroup = groupService
							.getGroupByGroupName(groupName);
					GroupMember groupMember = new GroupMember(user,
							createdGroup, MemberStatus.ACCEPTED);
					memberService.saveMember(groupMember);

					response.setCode("createGroup001");
					response.setMessage(msgConfig.getProperty("createGroup001"));
					response.setGroupID(createdGroup.getGroupID());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("createGroup002");
			response.setMessage(msgConfig.getProperty("createGroup002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@GET
	@Path("group-list-for-owner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getGroupList(@QueryParam("userID") String ownerId) {
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();

		try {

			List<Group> list = groupService.getGroupListForOwner(ownerId);

			for (Group group : list) {
				User owner = group.getGroupOwner();
				GroupDTO dto = new GroupDTO();
				dto.setGroupID(group.getGroupID());
				dto.setGroupName(group.getGroupName());
				dto.setOwnerUserID(owner.getUserID());
				dto.setOwnerName(owner.getName());
				dto.setOwnerCity(owner.getCity());
				dto.setOwnerCountry(owner.getCity());
				groupList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getOwnerGroupList001");
			response.setMessage(msgConfig.getProperty("getOwnerGroupList001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(groupList).build();
	}

	@POST
	@Path("add-member-to-group")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response addMemberToGroup(GroupMemberDTO member) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();

		try {
			response.setCode("addMemberToGroup001");
			response.setMessage(msgConfig.getProperty("addMemberToGroup001"));

			User savedUser = userService.getUser(member.getUserKey());
			Group savedgroup = groupService.getGroupByGroupId(member
					.getGroupID());

			if (savedUser != null && savedgroup != null) {
				GroupMember groupMember = new GroupMember(savedUser,
						savedgroup, MemberStatus.WAITING);
				memberService.saveMember(groupMember);
			} else {

				response.setCode("addMemberToGroup002");
				response.setMessage(msgConfig
						.getProperty("addMemberToGroup002"));
			}
			/*
			 * else { //Send Push Notification to user User groupOwner =
			 * groupService.getGroupOwner(savedgroup.getGroupID());
			 * 
			 * if(groupOwner != null) {
			 * 
			 * String certificateFileName =
			 * apnConfigurationProperties.getProperty("certificate.file");
			 * String password =
			 * apnConfigurationProperties.getProperty("certificate.file.password"
			 * );
			 * 
			 * StringBuilder message = new
			 * StringBuilder(groupOwner.getUserID());
			 * message.append(" has requested you to join group ");
			 * message.append(savedgroup.getGroupName());
			 * 
			 * Resource resource =
			 * appContext.getResource("classpath:/apn/"+certificateFileName);
			 * 
			 * try {
			 * MathPlayNLearnUtil.sendNotification(resource.getFile().toString
			 * (), password, savedUser.getDeviceToken(), message.toString()); }
			 * catch (Exception e) { e.printStackTrace();
			 * logger.fatal(MathPlayNLearnUtil
			 * .getExceptionDescriptionString(e)); } } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("addMemberToGroup002");
			response.setMessage(msgConfig.getProperty("addMemberToGroup002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@GET
	@Path("get-group-members/{groupID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response listGroupMembers(@PathParam("groupID") long groupID) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		List<GroupMemberInfoDTO> membersInfoList = null;

		try {
			membersInfoList = memberService.getMembersInfoByGroup(groupID);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getGroupMembers001");
			response.setMessage(msgConfig.getProperty("getGroupMembers001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(membersInfoList).build();
	}

	@POST
	@Path("delete-member-from-group")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response deleteMember(GroupMemberDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		response.setCode("deleteMemberFromGroup001");
		response.setMessage(msgConfig.getProperty("deleteMemberFromGroup001"));

		try {
			memberService.deleteGroupMember(dto.getGroupID(), dto.getUserKey());

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("deleteMemberFromGroup002");
			response.setMessage(msgConfig
					.getProperty("deleteMemberFromGroup002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("delete-group")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response deleteGroup(GroupMemberDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		response.setCode("deleteGroup001");
		response.setMessage(msgConfig.getProperty("deleteGroup001"));

		try {
			Group savedGroup = groupService.getGroupByGroupId(dto.getGroupID());

			if (savedGroup != null) {
				groupService.deleteGroup(savedGroup);

			} else {
				response.setCode("deleteGroup002");
				response.setMessage(msgConfig.getProperty("deleteGroup002"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("deleteGroup003");
			response.setMessage(msgConfig.getProperty("deleteGroup003"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("get-group-score")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getGroupScore(GroupScoreDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		List<Object[]> list = null;

		try {
			Game savedGame = gameService.getGameByNameAndClass(
					dto.getGameName(), dto.getGameClass());

			if (savedGame != null) {
				list = detailsService.getScoreForGroup(dto.getGroupID(),
						savedGame.getGameId());
			} else {
				response.setCode("getGroupScore001");
				response.setMessage(msgConfig.getProperty("getGroupScore001"));

				return Response.ok(response).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getGroupScore002");
			response.setMessage(msgConfig.getProperty("getGroupScore002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(list).build();
	}

	@GET
	@Path("group-list-for-member/{userID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getGroupListForMember(@PathParam("userID") String userID) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		List<GroupDTO> list = null;
		try {
			list = groupService.getGroupListForMember(userID);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getMemberGroupList001");
			response.setMessage(msgConfig.getProperty("getMemberGroupList001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(list).build();
	}

	@GET
	@Path("get-total-user-score/{groupID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getTotalScoreForUser(@PathParam("groupID") long groupID) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		List<Object[]> list = null;
		try {
			list = detailsService.getTotalScoreForUser(groupID);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getTotalUserScore001");
			response.setMessage(msgConfig.getProperty("getTotalUserScore001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(list).build();
	}

	@GET
	@Path("get-invitations/{userID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getInvitations(@PathParam("userID") String userID) {
		List<GetInvitationsDTO> list = null;
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {

			list = memberService.getGroupInvitationsForUser(userID);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getInvitations001");
			response.setMessage(msgConfig.getProperty("getInvitations001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(list).build();
	}

	@GET
	@Path("get-invitation-count/{userID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getInvitationCount(@PathParam("userID") String userID) {
		Long[] count = new Long[1];
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {
			count[0] = memberService.getInvitationCount(userID);

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("getInvitationcount001");
			response.setMessage(msgConfig.getProperty("getInvitationcount001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(count).build();
	}

	@POST
	@Path("update-invitation-status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response updateInvitationStatus(UpdateInvitationStatusDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		response.setCode("updateInvitationStatus003");
		response.setMessage(msgConfig.getProperty("updateInvitationStatus003"));

		try {
			GroupMember member = memberService.getGroupMemberByID(
					dto.getGroupID(), dto.getMemberID());
			if (member == null) {
				response.setCode("updateInvitationStatus002");
				response.setMessage(msgConfig
						.getProperty("updateInvitationStatus002"));

			} else {
				member.setStatus(dto.getStatus());
				memberService.saveOrUpdate(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("updateInvitationStatus001");
			response.setMessage(msgConfig
					.getProperty("updateInvitationStatus001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("leave-group")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response leaveGroup(@Valid LeaveGroupDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		response.setCode("leaveGroup001");
		response.setMessage(msgConfig.getProperty("leaveGroup001"));

		try {
			memberService.deleteGroupMember(dto.getGroupID(), dto.getUserKey());

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("leaveGroup002");
			response.setMessage(msgConfig.getProperty("leaveGroup002"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}

	@POST
	@Path("get-user-position-all-games")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getUserPositionScoreInAllGames(@Valid LeaveGroupDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		List<UserRankResponseDTO> responseDto = new ArrayList<UserRankResponseDTO>();

		Map<Long, List<UserGameScoreDTO>> allUserMap = null;
		Map<Long, Long> userMap = null;

		try {
			allUserMap = detailsService
					.getScoreForEachGameByGroupForAllUsers(dto.getGroupID());
			userMap = detailsService.getScoreForEachGameForUser(dto
					.getUserKey());

			for (Entry<Long, Long> entry : userMap.entrySet()) {

				Long userScore = entry.getValue();
				UserRankResponseDTO uRankResponseDTO = null;
				int rank = 1;
				List<UserGameScoreDTO> list = allUserMap.get(entry.getKey());

				for (UserGameScoreDTO dto2 : list) {
					uRankResponseDTO = new UserRankResponseDTO();
					uRankResponseDTO.setGameName(dto2.getGameName());
					uRankResponseDTO.setGameClass(dto2.getGameClass());
					long otherUserScore = dto2.getUserScore();
					if (otherUserScore > userScore) {
						rank++;
					}
				}

				if (uRankResponseDTO != null) {
					uRankResponseDTO.setRank(rank);
					uRankResponseDTO.setScore(userScore);
					responseDto.add(uRankResponseDTO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("userRankInAllGames001");
			response.setMessage(msgConfig.getProperty("userRankInAllGames001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(responseDto).build();
	}

	@POST
	@Path("save-high-score-notification")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response saveHighScoreNotification(GameDetailsDTO dto) {
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();

		try {
			List<GroupDTO> groupList = groupService
					.getGroupListForMemberAndAdmin(dto.getUserID());

			if (groupList.size() > 0) {
				List<Long> groupIDList = new ArrayList<Long>();
				for (GroupDTO dto2 : groupList) {
					groupIDList.add(dto2.getGroupID());
				}

				//Get details of users with score in the game.
				List<Object[]> list = detailsService.getUsereDetailsWithHighestScoreForGameInGroups(groupIDList, dto.getGameID());
				long highestScore = 0;
				if(list.size() > 0) {
					Object arr[] = list.get(0);
					highestScore = (Long)arr[0];
				}
				
				
				/*for(Object[] obj : list) {
					if(dto.getUserKey() == (Long)obj[1]) {
						continue;
					}
					oldHighestScore = (Long) obj[0];
					arr = obj;
				}*/

				if (dto.getUserScore() >= highestScore) {
					Notification notification = new Notification();
					notification.setGameName(dto.getGameName());
					notification.setGameClass(dto.getGameClass());
					notification.setNewUserName(dto.getName());
					notification.setNewUserCity(dto.getCity());
					notification.setNewUserCountry(dto.getCountry());
					notification.setNewScore(dto.getUserScore());
					/*notification.setOldUserName((String)arr[3]);
					notification.setOldUserCity((String)arr[4]);
					notification.setOldUserCountry((String)arr[5]);
					notification.setOldScore(oldHighestScore);*/
					
					List<Object[]> userList = detailsService.getUsereDetailsForGameInGroups(groupIDList);
					
					for(Object[] obj : userList) {
						if(!((Long)obj[0] == dto.getUserKey())) {
							User savedUser = userService.getUser((Long)obj[0]);
							notification.getUsers().add(savedUser);
							notificationService.saveNotification(notification);
							savedUser = null;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("saveNotification001");
			response.setMessage(msgConfig.getProperty("saveNotification001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(response).build();
	}
	
	@GET
	@Path("get-notification-count/{userKey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(rollbackFor = Exception.class)
	public Response getNotificationCountForUser(@PathParam("userKey") long userKey) {
		List<Notification> list = null;
		Long[] count = new Long[1];
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {

			list = notificationService.getNotificationForUser(userKey);
			count[0] = new Long(list.size());

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("countNotification001");
			response.setMessage(msgConfig.getProperty("countNotification001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(count).build();
	}
	
	@GET
	@Path("get-notifications/{userKey}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getNotificationsForUser(@PathParam("userKey") long userKey) {
		List<Notification> list = null;
		List<NotificationDTO> dtoList = new ArrayList<NotificationDTO>();
		MathPlayNLearnServiceResponse response = new MathPlayNLearnServiceResponse();
		try {

			list = notificationService.getNotificationForUser(userKey);
			if(list.size() > 0) {
				for(Notification notification : list) {
					NotificationDTO dto = new NotificationDTO();
					dto.setNotificationID(notification.getNotificationID());
					dto.setGameClass(notification.getGameClass());
					dto.setGameName(notification.getGameName());
					dto.setNewUserName(notification.getNewUserName());
					dto.setNewUserCity(notification.getNewUserCity());
					dto.setNewUserCountry(notification.getNewUserCountry());
					dto.setNewScore(notification.getNewScore());
					dtoList.add(dto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("countNotification001");
			response.setMessage(msgConfig.getProperty("countNotification001"));
			logger.fatal(MathPlayNLearnUtil.getExceptionDescriptionString(e));
			throw new WebApplicationException(Response.ok(response).build());
		}
		return Response.ok(dtoList).build();
	}
}
