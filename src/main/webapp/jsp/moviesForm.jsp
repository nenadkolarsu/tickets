<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="top.jsp"%>

	<div id="wrapper">

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<div class='panel panel-default'>
						<div class='panel-heading'>${title}</div>
						<div class='panel-body'>
							<div class='row'>
								<div class='col-lg-6'>

									<form:form method="POST" action="save_movies.html"
										modelAttribute="movies">

										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />

										<form:input type="hidden" name="id" path="id"
											value="${movies.id}" />

										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-oxygen'>Code</label>
											<form:input type="text" class="form-control " name="code"
												path="code" value="${movies.code}" autofocus="true"
												placeholder='Insert code' />
											<p class="help-block">
												<form:errors path="code" class="help-block " />
											</p>
										</div>


										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-awesome'>Name</label>
											<form:input type="text" class="form-control" id="name"
												path="name" placeholder='Insert name'
												value="${movies.name}" />
											<p class="help-block">
												<form:errors path="name" class="help-block" />
											</p>
										</div>

										<div class='form-group'>
											<label class='font-roboto'>Remark</label>
											<%-- 									<textarea class="form-control" name='remark' path='remark'  placeholder='Unesite napomenu' value="${task.remark}"></textarea> --%>
											<form:textarea type="text" class="form-control" id="remark"
												path="remark" value="${movies.remark}" placeholder='Enter remark'/>
											<p class="help-block">
												<form:errors path="remark" class="help-block" />
											</p>
										</div>

<%-- 										<c:out value="${model.movies.status}" /> --%>

<%-- 										<c:if test="${movies.status}"> --%>
<%-- 											<c:out value="${movies.code}" /> --%>
<%-- 										</c:if> --%>

										<c:set var="salary" scope="session" value="checked">
										</c:set>
										<c:set var="salary1" scope="session" value="">
										</c:set>

										<c:if test="${movies.status == true}">
											<c:set var="salary" scope="session" value="checked" />
											<c:set var="salary1" scope="session" value="" />
										</c:if>

										<c:if test="${movies.status == false}">
											<c:set var="salary1" scope="session" value="checked" />
											<c:set var="salary" scope="session" value="" />
										</c:if>

										<c:if test="${(empty salary) and (empty salary1)}">
											<c:set var="salary" scope="session" value="checked" />
										</c:if>

										<div class='form-group spacer-bottom-30'>
											<label>Status</label><br /> <label class="radio-inline">
												<input type="radio" name="status" value="1" path='status'
												<c:out value="${salary}"/> />
											</label> <label class="radio-inline"> 
											<%--                                     	<input type="radio" class="col-sm-1" name="status"  path='status' value="${task.status}">Ne --%>
												<input type="radio" class="col-sm-1" name="status"
												value="0" path='status' <c:out value="${salary1}"/> />
											</label>
										</div>

										<button type="submit" class="btn-lg button-novi">
											<i class='fa fa-floppy-o'></i> Save
										</button>
<!-- 										<button type="reset" class="btn-lg button-novi"> -->
<!-- 											<i class='fa fa-reply'></i> Cancel -->
<!-- 										</button> -->
										<input type="button" onclick="location.href='/movies.html';" value="Cancel"  class="btn-lg button-novi"/>
									</form:form>
								</div>
							</div>
						</div>
					</div>
					<!-- end of panel -->
				</div>
			</div>

		</div>

	</div>

<%-- 	<%@ include file="scripts.jsp"%> --%>
</body>
</html>