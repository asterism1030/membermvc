package com.kitri.member.model;

import java.util.List;
import java.util.Map;

public interface MemberDao {
	
	int idCheck(String id); 
	List<ZipDto> zipSearch(String dong);
	int register(MemberDetailDto memberDetailDto);
	
	//Dto�� ������ ��� 1���� ���ڸ� �ѱ�� �ִ�
	//Map�� ��� key�� value���� �ѱ�Ƿ� 
	//�ߺ��� ��찡 �߻����� �ʴ´�
	MemberDto login(Map<String, String> map);
}
