package com.kitri.member.model;

import java.sql.*;
import java.util.*;

import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int idCheck(String id) {
		int count = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		      conn = DBConnection.makeConnection();
		      String sql ="";
		      sql += "select count(id) \n";
		      sql += "from member \n";
		      sql += "where id = ?";
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      rs.next();
		      count = rs.getInt(1);
		   } catch (SQLException e) {
			  count = 1;
		      e.printStackTrace();
		   } finally {
		      DBClose.close(conn, pstmt, rs);
		   }
		
		return count;
	}

	
	@Override
	public List<ZipDto> zipSearch(String dong) {
		List<ZipDto> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		      conn = DBConnection.makeConnection();
		      String sql ="";
		      sql += "select substr(zipcode, 1, instr(zipcode, '-')-1) zip1, \n";
		      sql += "		substr(zipcode, instr(zipcode, '-')+1) zip2, \n";
		      sql += "		sido, gugun, dong, nvl(bunji, ' ') bunji \n";
		      sql += "from zipcode \n";
		      sql += "where dong like '%'||?||'%'"; // "%" + dong + "%"
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, dong);
		      rs = pstmt.executeQuery();
		      
		      while(rs.next()) {
		    	  ZipDto zip = new ZipDto();
		    	  
		    	  zip.setZip1(rs.getString("zip1"));
		    	  zip.setZip2(rs.getString("zip2"));
		    	  zip.setSido(rs.getString("sido"));
		    	  zip.setGugun(rs.getString("gugun"));
		    	  zip.setDong(rs.getString("dong"));
		    	  zip.setBunji(rs.getString("bunji"));
		    	  
		    	  list.add(zip);
		      }
		      
		   } catch (SQLException e) {
		      e.printStackTrace();
		   } finally {
			   DBClose.close(conn, pstmt, rs);
		   }
		
		return list;
	}

	@Override
	public int register(MemberDetailDto memberDetailDto) {
		int count = 0;
		Connection conn = null;
		PreparedStatement psmt = null; //공유된 데이터에서 연결..
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "";
			sql += "insert all into member (id, name, pass, email1, email2, joindate) ";
		    sql += "values(?,?,?,?,?,sysdate)";
		    sql += " into member_detail (id, zip1, zip2, addr1, addr2, tel1, tel2, tel3) ";
		    sql += "values(?,?,?,?,?,?,?,?) ";
		    sql += "select * from dual";
		    
		    //prepareStatement(sql)은 createStatement완 달리
		    //sql을 인자로 가져가고 실행할땐 인자가 없다 (반대!!)
		    psmt = conn.prepareStatement(sql);
		    int idx = 1;
		    psmt.setString(idx++, memberDetailDto.getId());
		    psmt.setString(idx++, memberDetailDto.getName());
		    psmt.setString(idx++, memberDetailDto.getPass());
		    psmt.setString(idx++, memberDetailDto.getEmail1());
		    psmt.setString(idx++, memberDetailDto.getEmail2());
		    psmt.setString(idx++, memberDetailDto.getId());
		    psmt.setString(idx++, memberDetailDto.getZip1());
		    psmt.setString(idx++, memberDetailDto.getZip2());
		    psmt.setString(idx++, memberDetailDto.getAddr1());
		    psmt.setString(idx++, memberDetailDto.getAddr2());
		    psmt.setString(idx++, memberDetailDto.getTel1());
		    psmt.setString(idx++, memberDetailDto.getTel2());
		    psmt.setString(idx++, memberDetailDto.getTel3());
		    count = psmt.executeUpdate();		
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt);
		}
		
		return count;
	}

	@Override
	public MemberDto login(Map<String, String> map) {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "";
			sql += "select name, id, email1, email2 \n";
			sql += "from member \n";
			sql += "where id = ? and pass = ?";	//PreparedStatement는 치환변수이기 때문에 ?를 사용할 수 있다
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, map.get("userid"));   //첫번째 변수 ? 에 userid를 넣음
			psmt.setString(2, map.get("userpass")); //두번째 변수 ? 에 pass를 넣음 (userid와 pass는 MemberServiceImpl->login에서 map에 넣은 값)
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				memberDto.setEmail1(rs.getString("email1"));
				memberDto.setEmail2(rs.getString("email2"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return memberDto;
	}

}
