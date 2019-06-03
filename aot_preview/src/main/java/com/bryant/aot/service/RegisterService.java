package com.bryant.aot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bryant.aot.entity.AotMember;
import com.bryant.aot.repository.AotMemberDao;
import com.bryant.aot.util.JSONUtil;
import com.bryant.aot.util.LogUtil;


@Service
public class RegisterService {

	@Autowired AotMemberDao aotMemDao;
	
	/**
	 * 檢查名稱是否重複
	 * @param username
	 * @return
	 */
	public String checkAcc(String username) {
		try {
			Integer cnt = aotMemDao.findByUsername(username);
			if(cnt>0) {
				LogUtil.log.warn("checkAcc username==>{}, Exist",username);
				return "EXIST";
			}
			LogUtil.log.warn("checkAcc username==>{}, OK",username);
			return "OK";
		}catch(Exception e) {
			LogUtil.log.error("checkAcc Error username ==>{}",username);
			LogUtil.log.error("Error Msg ==>{}",e.getMessage());
		}
		return "FAILED";
	}

	/**
	 * 自動加入後綴數字
	 * @param username
	 * @return
	 */
	public String suffixUsername(String username) {
		String realName = "";
		int tempNum = 1 ; 
		try {
			while(true) {
				String tempName = username + tempNum;
				if("OK".equals(checkAcc(tempName))) {
					realName = tempName;
					break;
				}
				tempNum++;
				
			}
			LogUtil.log.warn("Auto suffix username==>{}",username);
			return realName;			
		}catch(Exception e) {
			LogUtil.log.error("suffixUsername Error username=={}",username);
			LogUtil.log.error("Error Msg ==>{}",e.getMessage());
		}
		return "FAILED";
	}

	/**
	 * 存入DB並回傳Json字串
	 * @param username
	 * @return
	 */
	public String saveAndReturnJsonString(String username) {
		AotMember aotMem = new AotMember();
		Map<String, String> jsonString = new HashMap<String, String>();
		try {
			aotMem.setUsername(username);
			aotMem = aotMemDao.save(aotMem);
			jsonString.put("username", aotMem.getUsername());
			jsonString.put("userId", String.valueOf(aotMem.getUserId()));
			
			String jsonStr = JSONUtil.convertObjectToJsonString(jsonString);
			LogUtil.log.warn("register completed!! ==>{}",jsonStr);
			return jsonStr;
		} catch (Exception e) {
			LogUtil.log.error("save Error username ==>{}",username);
			LogUtil.log.error("Error Msg ==>{}",e.getMessage());
		}
		return "FAILED";
	}
}
