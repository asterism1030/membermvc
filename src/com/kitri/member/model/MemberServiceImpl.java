package com.kitri.member.model;

import java.sql.*;
import java.util.*;

/*
 * 어떠한 처리는 거의 없이 리턴만!! 
 * Model 에서 Action의 역할.
 */
public class MemberServiceImpl implements MemberService {
	
	MemberDao memberDao;
	
	//init()에서 작성하지 않는 이유는
	//servlet 이 생성되는 순간에 init()이 만들어지고
	//class 가 생성되는 순간에 생성자가 만들어진다
	public MemberServiceImpl() {
		memberDao = new MemberDaoImpl();
	}

	@Override
	public int idCheck(String id) {
		return memberDao.idCheck(id);
	}

	@Override
	public List<ZipDto> zipSearch(String dong) {
		return memberDao.zipSearch(dong);
	}

	@Override
	public int register(MemberDetailDto memberDetailDto) {
		return memberDao.register(memberDetailDto);
	}

	@Override
	public MemberDto login(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpass", pass);
		
		return memberDao.login(map);
	}

}
