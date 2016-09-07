package com.kitri.member.model;

import java.util.List;

public interface MemberService {

	int idCheck(String id);   //select count(id) 이므로. id가 존재할 경우 1, X경우 0
	List<ZipDto> zipSearch(String dong); //'동'을 검색할 때 여러 결과값이 나오므로
	int register(MemberDetailDto memberDetailDto); //select count(~).
	
	MemberDto login(String id, String pass);
	
}
