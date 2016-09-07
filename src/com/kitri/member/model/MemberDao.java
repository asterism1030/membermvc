package com.kitri.member.model;

import java.util.List;
import java.util.Map;

public interface MemberDao {
	
	int idCheck(String id); 
	List<ZipDto> zipSearch(String dong);
	int register(MemberDetailDto memberDetailDto);
	
	//Dto를 못만들 경우 1개의 인자만 넘길수 있다
	//Map의 경우 key와 value값을 넘기므로 
	//중복인 경우가 발생하지 않는다
	MemberDto login(Map<String, String> map);
}
