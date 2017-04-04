package com.newlecture.web.dao.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.data.entity.Notice;
import com.newlecture.web.data.entity.NoticeView;

public class MySQLNoticeDao implements NoticeDao{
	
	@Override
	public int update(Notice notice) {
		String sql = "UPDATE NOTICE SET TITLE = ?,CONTENT = ? WHERE CODE= ?";
		int result =0 ;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass");		
			PreparedStatement st = con.prepareStatement(sql);			
			st.setString(1, notice.getTitle());
			st.setString(2, notice.getContent());
			st.setString(3, notice.getCode());
						
			result = st.executeUpdate();
			
			st.close();
			con.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int delete(String code) {
		String sql = "DELETE FROM NOTICE WHERE CODE= ?";
		int result =0 ;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶 
		
			PreparedStatement st = con.prepareStatement(sql);			
			System.out.println("code:"+code);
			st.setString(1, code);
			System.out.println("�씡�뒪�걧�듃 �쟾�뿉");
			result = st.executeUpdate();
			System.out.println("result: "+result);
			System.out.println("�씡�뒪�걧�듃 �썑�뿉");
			st.close();
			con.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("吏��슦湲곕뒗 �꽦怨�");
		return result;
	}

	
	@Override
	public NoticeView get(String code) {
		String sql = "SELECT * FROM NOTICE_VIEW WHERE CODE=?"; //�뜲�씠�꽣�뿉留� ? �궗�슜 媛��뒫�븯湲� �븣臾몄뿉, title�� "+field+"�씪怨� ��
		NoticeView notice = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, code);
					
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				notice = new NoticeView();
				notice.setCode(rs.getString("CODE"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setWriter(rs.getString("WRITER"));
				notice.setRegDate(rs.getDate("REGDATE"));
				notice.setHit(rs.getInt("HIT"));
				//NoticeView 而щ읆
				notice.setWriterName(rs.getString("WRITER_NAME"));
				notice.setCommentCount(rs.getInt("COMMENT_COUNT"));
		
			}

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;

	}
	
	@Override
	public NoticeView getPrev(String code) {
		String sql = "SELECT * FROM NOTICE WHERE CAST(CODE AS unsigned)< CAST(? AS unsigned) ORDER BY REGDATE DESC LIMIT 0,1;"; //�뜲�씠�꽣�뿉留� ? �궗�슜 媛��뒫�븯湲� �븣臾몄뿉, title�� "+field+"�씪怨� ��
		NoticeView notice = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, code);
					
			ResultSet rs = st.executeQuery();
			
		
			while (rs.next()) {
				notice = new NoticeView();
				notice.setCode(rs.getString("CODE"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setWriter(rs.getString("WRITER"));
				notice.setRegDate(rs.getDate("REGDATE"));
				notice.setHit(rs.getInt("HIT"));
				//NoticeView 而щ읆
				notice.setWriterName(rs.getString("WRITER_NAME"));
				notice.setCommentCount(rs.getInt("COMMENT_COUNT"));
		
			}

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;

	}
	


	@Override
	public NoticeView getNext(String code) {
		String sql = "SELECT * FROM NOTICE WHERE CAST(CODE AS unsigned)> CAST(? AS unsigned) ORDER BY REGDATE LIMIT 0,1"; //�뜲�씠�꽣�뿉留� ? �궗�슜 媛��뒫�븯湲� �븣臾몄뿉, title�� "+field+"�씪怨� ��
		NoticeView notice = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, code);
					
			ResultSet rs = st.executeQuery();
			
		
			while (rs.next()) {
				notice = new NoticeView();
				notice.setCode(rs.getString("CODE"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setWriter(rs.getString("WRITER"));
				notice.setRegDate(rs.getDate("REGDATE"));
				notice.setHit(rs.getInt("HIT"));
				//NoticeView 而щ읆
				notice.setWriterName(rs.getString("WRITER_NAME"));
				notice.setCommentCount(rs.getInt("COMMENT_COUNT"));
		
			}

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;

	}
	
	public List<NoticeView> getList() {
		return getList(1, "TITLE", "");
	}

	
	public List<NoticeView> getList(int page) {
		return getList(page, "TITLE", "");
	}

	public List<NoticeView> getList(int page, String field, String query) {
		String sql = "SELECT * FROM NOTICE_VIEW WHERE BINARY "+field+" LIKE ? LIMIT ?,10"; //�뜲�씠�꽣�뿉留� ? �궗�슜 媛��뒫�븯湲� �븣臾몄뿉, title�� "+field+"�씪怨� ��
		List<NoticeView> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, '%'+query+'%');
			st.setInt(2, 10*(page-1));
			
			ResultSet rs = st.executeQuery();
			
			NoticeView notice = null;

			while (rs.next()) {
				notice = new NoticeView();
				notice.setCode(rs.getString("CODE"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setWriter(rs.getString("WRITER"));
				notice.setRegDate(rs.getDate("REGDATE"));
				notice.setHit(rs.getInt("HIT"));
				//NoticeView 而щ읆
				notice.setWriterName(rs.getString("WRITER_NAME"));
				notice.setCommentCount(rs.getInt("COMMENT_COUNT"));
				
				list.add(notice);
			}

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public int getSize() {
		return getSize("TITLE", "");
	}


	public int getSize(String field, String query) {
		String sql = "SELECT COUNT(CODE) SIZE FROM NOTICE WHERE BINARY "+field+" LIKE ?"; //NOTICE_VIEW濡� �븯寃뚮릺硫� JOIN�븳 寃곌낵�룄 寃��깋�맖
		int size = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, '%'+query+'%');
			
			ResultSet rs = st.executeQuery();

			if(rs.next())
				size = rs.getInt("SIZE");

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return size;
	}


	public int add(Notice notice) {
		String codeSql = "SELECT MAX(CAST(CODE AS UNSIGNED))+1 CODE FROM NOTICE";
		
		String sql = "INSERT INTO NOTICE(CODE,TITLE,WRITER,CONTENT) VALUES(?,?,?,?)"; 
		int result = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			
			Statement codeSt = con.createStatement();
			ResultSet rs = codeSt.executeQuery(codeSql);
			rs.next();
			String code = rs.getString("CODE");
			rs.close();
			codeSt.close();
			
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, code);
			st.setString(2, notice.getTitle());
			st.setString(3, notice.getWriter());
			st.setString(4, notice.getContent());
			
			result = st.executeUpdate();
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public int add(String title, String content, String writer) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		return add(notice);
	}


	@Override
	public int update(String title, String content, String code) {
	
		Notice notice =new Notice();
		notice.setCode(code);
		notice.setTitle(title);
		notice.setContent(content);		
		
		return update(notice);
	}


	@Override
	public String lastCode() {
		String sql = "SELECT MAX(CAST(CODE AS unsigned)) CODE FROM NOTICE"; //NOTICE_VIEW濡� �븯寃뚮릺硫� JOIN�븳 寃곌낵�룄 寃��깋�맖
		String code="1";	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://211.238.142.84/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8"; // DB�뿰寃�
			Connection con = DriverManager.getConnection(url, "newlec", "sclass"); // �뱶�씪�씠釉� 濡쒕뱶
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if(rs.next())
				code = rs.getString("CODE");

			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return code;
	}
}
