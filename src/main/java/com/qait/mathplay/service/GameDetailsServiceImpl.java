package com.qait.mathplay.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qait.mathplay.dao.IGameDetailsDao;
import com.qait.mathplay.dao.domain.GameDetails;
import com.qait.mathplay.dto.UserGameScoreDTO;

@Service("gameDetailsService")
public class GameDetailsServiceImpl implements IGameDetailsService {

	@Autowired
	private IGameDetailsDao gameDetailsDao;

	@Override
	public void saveGameDetails(GameDetails details) {
		gameDetailsDao.saveOrUpdate(details);
	}

	@Override
	public GameDetails getGameDetailsByUserAndGame(Long userID, Long gameId) {
		return gameDetailsDao.getGameDetailsByUserAndGame(userID, gameId);
	}

	@Override
	public List<Object[]> getScoreForGroup(long groupID, long gameID) {
		return gameDetailsDao.getScoreForGroup(groupID, gameID);
	}

	@Override
	public List<Object[]> getTotalScoreForUser(long groupID) {
		return gameDetailsDao.getTotalScoreForUser(groupID);
	}

	@Override
	public List<UserGameScoreDTO> getScoreForGroupAllGames(long groupID) {
		List<Object[]> list = gameDetailsDao.getScoreForGroupAllGames(groupID);
		return getUserGameScoreDTOList(list);
	}

	@Override
	public Map<Long, List<UserGameScoreDTO>> getScoreForEachGameByGroupForAllUsers(
			long groupID) {
		Map<Long, List<UserGameScoreDTO>> map = new HashMap<Long, List<UserGameScoreDTO>>();
		List<UserGameScoreDTO> dtoList = getScoreForGroupAllGames(groupID);

		for (UserGameScoreDTO dto : dtoList) {
			Long gmaeID = dto.getGameID();
			if (map.containsKey(gmaeID)) {
				map.get(gmaeID).add(dto);
			} else {
				List<UserGameScoreDTO> list = new ArrayList<UserGameScoreDTO>();
				list.add(dto);
				map.put(gmaeID, list);
			}
		}
		return map;
	}

	@Override
	public List<UserGameScoreDTO> getScoreForUserAllGames(long userID) {
		List<Object[]> list = gameDetailsDao.getScoreForUserAllGames(userID);
		return getUserGameScoreDTOList(list);
	}

	@Override
	public Map<Long, Long> getScoreForEachGameForUser(long userID) {
		Map<Long, Long> map = new HashMap<Long, Long>();
		List<UserGameScoreDTO> dtoList = getScoreForUserAllGames(userID);

		for (UserGameScoreDTO dto : dtoList) {
			map.put(dto.getGameID(), dto.getUserScore());
		}
		return map;
	}

	@Override
	public List<Object[]> getUsereDetailsWithHighestScoreForGameInGroups(List<Long> groupIDList, long gameID) {
		return gameDetailsDao.getUsereDetailsWithHighestScoreForGameInGroups(groupIDList, gameID);
	}

	private List<UserGameScoreDTO> getUserGameScoreDTOList(List<Object[]> list) {
		List<UserGameScoreDTO> dtoList = new ArrayList<UserGameScoreDTO>();
		for (Object[] obj : list) {
			UserGameScoreDTO dto = new UserGameScoreDTO();
			dto.setGameID((Long) obj[0]);
			dto.setGameName((String) obj[1]);
			dto.setGameClass((String) obj[2]);
			dto.setUserKey((Long) obj[3]);
			dto.setUserID((String) obj[4]);
			dto.setUserName((String) obj[5]);
			dto.setCity((String) obj[6]);
			dto.setCountry((String) obj[7]);
			dto.setGameLevel((String) obj[8]);
			dto.setUserScore((Long) obj[9]);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Override
	public List<Object[]> getUsereDetailsForGameInGroups(List<Long> groupIDList) {
		return gameDetailsDao.getUsereDetailsForGameInGroups(groupIDList);
	}
}
