package com.kh.jsp.controller.board;

import com.kh.jsp.model.vo.*;
import com.kh.jsp.service.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.fileupload2.core.*;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

@WebServlet("/enroll.bo")
public class BoardEnrollController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BoardService service = new BoardService();

    public BoardEnrollController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            request.setAttribute("errorMsg", "로그인 후 이용 가능한 서비스입니다.");
            request.getRequestDispatcher("/views/common/error2.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/views/board/enrollForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {
            response.sendRedirect(request.getContextPath() + "/login.me");
            return;
        }

        if (JakartaServletFileUpload.isMultipartContent(request)) {

            // ===== 파일 업로드 처리 =====
            int fileMaxSize = 1024 * 1024 * 50; // 50MB
            int requestMaxSize = 1024 * 1024 * 60; // 전체 요청 크기
            String savePath = request.getServletContext().getRealPath("/resources/board-file/");

            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            upload.setFileSizeMax(fileMaxSize);
            upload.setSizeMax(requestMaxSize);

            try {
                List<FileItem> formItems = upload.parseRequest(request);

                Board b = new Board();
                Attachment at = null;
                b.setBoardWriter(loginMember.getMemberNo());
                b.setBoardType(1);

                for (FileItem item : formItems) {
                    if (item.isFormField()) { // 일반 폼 데이터
                        switch (item.getFieldName()) {
                            case "category":
                                b.setCategoryNo(Integer.parseInt(item.getString(Charset.forName("UTF-8"))));
                                break;
                            case "title":
                                b.setBoardTitle(item.getString(Charset.forName("UTF-8")));
                                break;
                            case "content":
                                b.setBoardContent(item.getString(Charset.forName("UTF-8")));
                                break;
                        }
                    } else { // 파일 데이터
                        String originName = item.getName();
                        if (originName != null && !originName.isEmpty()) {
                            // 고유 파일명 생성
                            String tmpName = "kh_" + System.currentTimeMillis() + ((int) (Math.random() * 100000) + 1);
                            String type = originName.substring(originName.lastIndexOf("."));
                            String changeName = tmpName + type;;

                            File f = new File(savePath, changeName);
                            item.write(f.toPath());

                            at = new Attachment();
                            at.setOrginName(originName);
                            at.setChangeName(changeName);
                            at.setFilePath("resources/board-file/");
                        }
                    }
                }

                int result = service.insertBoard(b, at);

                if (result > 0) {
                    session.setAttribute("alertMsg", "게시글 등록 성공!");
                    response.sendRedirect(request.getContextPath() + "/list.bo");
                } else {
                    if (at != null) new File(savePath + at.getChangeName()).delete();
                    request.setAttribute("errorMsg", "게시글 등록 실패");
                    request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMsg", "파일 업로드 중 오류 발생");
                request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
            }

        } else {
            // ===== 일반 폼 처리 =====
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String categoryParam = request.getParameter("category");

            if (categoryParam == null || categoryParam.isEmpty()) {
                request.setAttribute("errorMsg", "카테고리를 선택해주세요.");
                request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
                return;
            }

            int categoryNo = Integer.parseInt(categoryParam);

            Board board = new Board();
            board.setBoardTitle(title);
            board.setBoardContent(content);
            board.setBoardWriter(loginMember.getMemberNo());
            board.setCategoryNo(categoryNo);
            board.setBoardType(1);

            int result = service.insertBoard(board, null);

            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/list.bo");
            } else {
                request.setAttribute("errorMsg", "게시글 등록 실패");
                request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
            }
        }
    }
}
