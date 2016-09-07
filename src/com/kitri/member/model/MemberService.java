package com.kitri.member.model;

import java.util.List;

public interface MemberService {

	int idCheck(String id);   //select count(id) �̹Ƿ�. id�� ������ ��� 1, X��� 0
	List<ZipDto> zipSearch(String dong); //'��'�� �˻��� �� ���� ������� �����Ƿ�
	int register(MemberDetailDto memberDetailDto); //select count(~).
	
	MemberDto login(String id, String pass);
	
}
