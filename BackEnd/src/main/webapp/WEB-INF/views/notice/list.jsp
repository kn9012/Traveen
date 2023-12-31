<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Traveen</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/notice.css" />
<link rel="stylesheet" href="../css/notice-list.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
	<!-- header -->
	<%@ include file="../include/header.jsp"%>

	<!-- section -->
	<section>
		<h2 class="title">공지사항</h2>
		<h4 class="subtitle">Traveen의 공지사항을 확인해보세요.</h4>
		<!-- top-box -->
		<div class="top-box">
			<div class="left">총 ${totalCnt}건</div>
			<form class="right" id="form-search" method="get">
				<input type="hidden" name="pgno" value="1" />
				<select
					name="key" id="key">
					<option value="">전체</option>
					<option value="title">제목</option>
				</select> <input type="text" name="word" id="word"
					placeholder="검색어를 입력해주세요." />
					<button id="btn-search" type="button">검색</button>
				<button type="button" id="btn-regist" style="<c:if test="${userinfo.position ne M}">display: none</c:if>">글 등록</button>
				
		</div>
		<div class="table-container">
			<!-- table -->
			<table>
				<!-- thead -->
				<thead>
					<tr>
						<th width="8%">NO.</th>
						<th width="67%">제목</th>
						<th width="15%">게시일</th>
						<th width="10%">조회수</th>
					</tr>
				</thead>
				<!-- tbody -->
				<tbody>

					<c:if test="${not empty notices}">
						<c:forEach var="notice" items="${notices}" varStatus="status">
							<tr>
								<td>${fn:length(notices) - status.index}</td>
									<td class="subject"><a
									href=# class="notice-title link-dark" data-no="${notice.idx}">${notice.title}</a></td>
								<td><c:out
										value="${fn:substring(notice.createDate, 0, 10)}" /></td>
								<td>${notice.viewCount}</td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${empty notices}">
						<tr>
							<td colspan="5">작성된 글이 없습니다.</td>
						</tr>
					</c:if>

				</tbody>
			</table>
			</form>
		</div>
		<!-- pagination -->
		<div class="pagination-container">
        	${navigation.navigator}
      	</div>
	</section>

	<form id="form-param" method="get" action="">
		<input type="hidden" name="pgno" id="pgno" value="${pgno}">
	</form>
	<form id="form-no-param" method="get" action="${root}/notice/view">
	  <input type="hidden" name="pgno" value="${pgno}">
	  <input type="hidden" name="key" value="${key}">
	  <input type="hidden" name="word" value="${word}">
	  <input type="hidden" id="idx" name="idx" value="">
	</form>

	<!-- footer -->
	<%@ include file="../include/footer.jsp"%>
	
	<script>
    
    let titles = document.querySelectorAll(".notice-title");
    titles.forEach(function (title) {
      title.addEventListener("click", function () {
        document.querySelector("#idx").value = this.getAttribute("data-no");
        document.querySelector("#form-no-param").submit();
      });
    });

    document.querySelector("#btn-regist").addEventListener("click", function () {
  	  let form = document.querySelector("#form-param");
        form.setAttribute("action", "${root}/notice/regist");
        form.submit();
    });
    
    document.querySelector("#btn-search").addEventListener("click", function () {
  	  let form = document.querySelector("#form-search");
        form.setAttribute("action", "${root}/notice/list");
        form.submit();
    });

	let pages = document.querySelectorAll(".page-link");
	pages.forEach(function (page) {
		page.addEventListener("click", function () {
			document.querySelector("#pgno").value = this.parentNode.getAttribute("data-pg");
			let form = document.querySelector("#form-param");
			console.log(form);
			form.setAttribute("action", "${root}/notice/list");
			form.submit();
		});

	});
	</script>
	
</body>

</html>