package com.kh.jsp.controller.board;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.jsp.model.vo.Attachment;
import com.kh.jsp.model.vo.Board;
import com.kh.jsp.model.vo.Member;
import com.kh.jsp.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertController
 */
@WebServlet(name = "insert.bo", urlPatterns = { "/insert.bo" })
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//enctype이 multipart/form-data일 때 request로 값 추출이 불가
		
		/*
		    commons-fileupload2-core : 멀티파트에 요청에 대한 처리기능을 구현하는 라이브러리
			commons-fileupload2-jakarta : javax.servlet -> jakarta.servlet 패키지를 사용하게 하는 라이브러리
			commons-io-2.20.0 : 파일 읽기/쓰기에 대한 스트림을 구현하고있는 라이브러리
		 * */
		
		//enctype이 multipart/form-data로 전송되었는지를 체크
		System.out.println(JakartaServletFileUpload.isMultipartContent(request));

		if(JakartaServletFileUpload.isMultipartContent(request)) {
			HttpSession session = request.getSession();
			
			//1. 파일용량제한(byte)
			int fileMaxSize = 1024 * 1024 * 50; //50MB
			int requestMaxSize = 1024 * 1024 * 60; //전체요청 크기 제한
			
			//2. 전달된 파일을 저장시킬 폴더 경로 가져오기
			String savePath = request.getServletContext().getRealPath("/resources/board-file/");
			
			//3. DiskFileItemFactory(파일을 임시로 저장) 객체 생성
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
			
			//4. JakartaServletFileUpload : http요청으로 전달된 파일데이터를 파싱 -> 개별 FileItem 객체로 변환
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			
			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);
			
			List<FileItem> formItems = upload.parseRequest(request);
			
			Board b = new Board();
			Attachment at = null;
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			b.setBoardWriter(loginMember.getMemberNo());
			
			for(FileItem item : formItems) {
				System.out.println(item);
				//업로드된 데이터가 일반 폼 필드인지, 파일인지 구분할 수 있음.
				if(item.isFormField()) {//일반파라미터
					switch(item.getFieldName()) {
						case "category":
							int categoryNo = Integer.parseInt(item.getString(Charset.forName("UTF-8")));
							b.setCategoryNo(categoryNo);
							break;
						case "title":
							b.setBoardTitle(item.getString(Charset.forName("UTF-8")));
							break;
						case "content":
							b.setBoardContent(item.getString(Charset.forName("UTF-8")));
							break;
					}
				} else { //파일
					String originName = item.getName(); //파일의 원래 이름
					
					if(originName.length() > 0) { //파일업로드가 실제로 되었을 때
						//파일명이 겹치면 덮어씌우기 때문에 고유한 파일명을 만들어주자
						String tmpName = "kh_" + System.currentTimeMillis() + ( (int)(Math.random() * 100000) + 1);
						String type = originName.substring(originName.lastIndexOf(".")); //확장자 추출
						String changeName = tmpName + type; //서버에 저장할 파일명
						
						File f = new File(savePath, changeName);
						item.write(f.toPath()); //지정한 경로에 파일이 업로드
						
						at = new Attachment();
						at.setOriginName(originName);
						at.setChangeName(changeName);
						at.setFilePath("resources/board-file/");
					}
				}
			}
			
			// b -> O
			// at ->  O , X
			
			int result = new BoardService().insertBoard(b, at);
			
			if(result > 0) { //성공
				session.setAttribute("alertMsg", "일반게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.bo");
			} else { //실패
				if(at != null){
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "일반게시글 작성 실패");
				request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}