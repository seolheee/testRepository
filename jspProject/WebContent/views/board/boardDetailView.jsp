<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("board");
	Attachment at = (Attachment)request.getAttribute("attachment");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#detail-area{
		border: 1px solid white;
	}
</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>
    
    <div class="outer">
        <br><br>
        <h2 align="center">일반게시판 상세보기</h2>
        <br><br>
        
            <table id="detail-area" align="center" border="1">
                <tr>
                    <th width="80">카테고리</th>
                    <td width="80"><%=b.getCategory() %></td>
                    <th width="80">제목</th>
                    <td width="300"><%=b.getBoardTitle() %></td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td><%=b.getBoardWriter() %></td>
                    <th>작성일</th>
                    <td><%=b.getCreateDate() %></td>
                </tr>
                <tr>
                    <th height="200">내용</th>
                    <td colspan="3"><%=b.getBoardContent() %></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                    	
                    	<!-- 첨부파일이 없을 경우 : 첨부파일이 없습니다. -->
                    	<%if(at == null){ %>
                    		첨부파일이 없습니다.
                    	<%}else{ %>
	                    	<!-- 첨부파일이 있을 경우 -->
	                    	<a href="<%=contextPath + at.getFilePath()%>/<%=at.getChangeName()%>" download=<%=at.getOriginName() %>><%=at.getOriginName() %></a>
	                    	<!-- /없으면 하나로 합칠 수 있음 -->
                    	<%} %>
                    </td>
                </tr>
            </table>
            <br>
            <%if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
				<div align="center">
					<button onclick="location.href='<%=contextPath %>/update.bo?bno=<%=b.getBoardNo() %>'" class="btn btn-info">수정하기</button>
					<button onclick="location.href='<%=contextPath %>/delete.bo?bno=<%=b.getBoardNo() %>'" class="btn btn-danger">삭제하기</button>
				</div>
			<%} %>
        <br><br>
    </div>
</body>
</html>