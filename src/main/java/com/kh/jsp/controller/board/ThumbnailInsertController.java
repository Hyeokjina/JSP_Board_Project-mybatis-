package com.kh.jsp.controller.board;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(JakartaServletFileUpload.isMultipartContent(request)) {
			HttpSession session = request.getSession();
			
			//1. 파일용량제한(byte)
			int fileMaxSize = 1024 * 1024 * 50; //50MB
			int requestMaxSize = 1024 * 1024 * 60; //전체요청 크기 제한
			
			//2. 전달된 파일을 저장시킬 폴더 경로 가져오기
			String savePath = request.getServletContext().getRealPath("/resources/thumbnail-file/");
			
			//3. DiskFileItemFactory(파일을 임시로 저장) 객체 생성
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
			
			//4. JakartaServletFileUpload : http요청으로 전달된 파일데이터를 파싱 -> 개별 FileItem 객체로 변환
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			
			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);
			
			List<FileItem> formItems = upload.parseRequest(request);
		
			Board b = new Board();
			ArrayList<Attachment> list = new ArrayList<>();
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			b.setBoardWriter(loginMember.getMemberNo());
			
			for(FileItem item : formItems) {
				System.out.println(item);
				//업로드된 데이터가 일반 폼 필드인지, 파일인지 구분할 수 있음.
				if(item.isFormField()) {//일반파라미터
					switch(item.getFieldName()) {
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
						
						Attachment at = new Attachment();
						at.setOriginName(originName);
						at.setChangeName(changeName);
						at.setFilePath("resources/thumbnail-file/");
						
						at.setFileLevel(item.getFieldName().equals("file1") ? 1 : 2);
						list.add(at);
					}
				}
			}
			
			int result = new BoardService().insertBoard(b, list);
			
			if(result > 0) { //성공
				session.setAttribute("alertMsg", "사진게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.th");
			} else { //실패
				for(Attachment at : list) {
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "사진게시글 작성 실패");
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