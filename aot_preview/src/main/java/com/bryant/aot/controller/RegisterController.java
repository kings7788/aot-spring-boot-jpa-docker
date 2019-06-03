package com.bryant.aot.controller;

import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bryant.aot.entity.AotMember;
import com.bryant.aot.repository.AotMemberDao;
import com.bryant.aot.service.RegisterService;
import com.bryant.aot.util.LogUtil;


@RestController
public class RegisterController {
	
	@Autowired private RegisterService registerService;

	/**
	 * 新會員註冊
	 * @param username
	 * @return json string with userId , username
	 */
	@PostMapping("/api/user")
	public String doRegister(@RequestParam("username") String username) {
			String result="";
			try {
				//確認新註冊的會員id是否被使用過 ,被使用過的話在末尾加入數字
				switch(registerService.checkAcc(username)) {
					case "OK":
						//username檢查沒問題,直接存入DB
						break;
					case "EXIST":
						//username已使用,加入後綴數字
						username = registerService.suffixUsername(username);
						if("FAILED".equals(username)){
							result = "doRegister failed when suffix";
							throw new Exception("doRegister failed when suffix");
						}
						break;
					case "FAILED":
						//checkAcc failed
						result = "doRegister failed when check";
						throw new Exception("doRegister failed  when check");
				}
				
				//username 存入DB
				result = registerService.saveAndReturnJsonString(username);
				if("FAILED".equals(result)){
					result = "doRegister failed when save";
					throw new Exception("doRegister failed when save");
				}
				return result;
			} catch (Exception e) {
				LogUtil.log.error(result);
			}
		return result;

	}
}
