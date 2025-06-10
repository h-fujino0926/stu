<%@ page language="java" contentType="text.html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		成績管理一覧
	</c:param>

	<c:param name="scripts"></c:param>
			<c:param name="content">
     	<section class="me-4">
     		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
     		<form method="get">
     			<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
     				<div class="col-4">
     					<label class="form-label" for="student-f1-select">入学年度</label>
     					<select class="form-select" id="student-f1--select" name="f1">
     						<option value="0">--------</option>
     						<c:forEach var="year" items="${ent_year_set}">
     							<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
     							<option value="${num}" <c:if test="${num=f2}"> selected</c:if>>${num}</option>
     						</c:forEach>
     					</select>
     				</div>
     				<div  class="col-4">
     					<label class="form-label" for="student"-f2-select">クラス</label>
     					<select class="form-select" id="studentf2-select" name="f2">
     						<option value="0">--------</option>
     						<c:forEach var="num" items="${class_num_set}">
     							<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
     							<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
     						</c:forEach>
     					</select>
     				</div>
		</section>
	</c:param>
</c:import>