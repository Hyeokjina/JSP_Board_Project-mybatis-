package com.kh.jsp.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * lombok은 자바에서 반복적으로 작성해야하는 코드를 자동으로 생성해주는 라이브러리이다.
 * getter/setter, toString, equals, hashCode
 * constructor등...
 * */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String phone;
	private String email;
	private String address;
	private String interest;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	public static Member insertCreateMember(String userId, 
										String userPwd, 
										String userName, 
										String phone, 
										String email, 
										String address, 
										String interest) {
		Member m = new Member();
		m.setMemberId(userId);
		m.setMemberPwd(userPwd);
		m.setMemberName(userName);
		m.setPhone(phone);
		m.setEmail(email);
		m.setAddress(address);
		m.setInterest(interest);
		
		return m;
	}
	
	public static Member updateCreateMember(String userId,
											String phone, 
											String email, 
											String address, 
											String interest) {
		Member m = new Member();
		m.setMemberId(userId);
		m.setPhone(phone);
		m.setEmail(email);
		m.setAddress(address);
		m.setInterest(interest);
		
		return m;
	}
}