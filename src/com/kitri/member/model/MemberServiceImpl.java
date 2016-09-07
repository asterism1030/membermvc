package com.kitri.member.model;

import java.sql.*;
import java.util.*;

/*
 * ��� ó���� ���� ���� ���ϸ�!! 
 * Model ���� Action�� ����.
 */
public class MemberServiceImpl implements MemberService {
	
	MemberDao memberDao;
	
	//init()���� �ۼ����� �ʴ� ������
	//servlet �� �����Ǵ� ������ init()�� ���������
	//class �� �����Ǵ� ������ �����ڰ� ���������
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
