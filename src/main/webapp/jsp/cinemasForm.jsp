<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="top.jsp"%>

	<div id="wrapper">

		<div id="page-wrapper">

<!-- 			<div class="row"> -->
<!-- 				<div class="col-lg-12"> -->
<!-- 					<h6 class="page-header"></h6>  -->
<!-- 				</div> -->
<!-- 			</div> -->

			<div class="row">
				<div class="col-lg-12">
					<div class='panel panel-default'>
						<div class='panel-heading'>${title}</div>
						<div class='panel-body'>
							<div class='row'>
								<div class='col-lg-6'>

									<form:form method="POST" action="save_cinemas.html"
										modelAttribute="cinemas">

										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />

										<form:input type="hidden" name="id" path="id"
											value="${cinemas.id}" />

										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-oxygen'>Code</label>

											<form:input type="text" class="form-control " name="code"
												path="code" value="${cinemas.code}" autofocus="true"
												placeholder='Insert code' />

											<p class="help-block">
												<form:errors path="code" class="help-block text-danger" />
											</p>
										</div>


										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-awesome'>Name</label>
											<%-- 									<input class="form-control" name='name' path='name' placeholder='Unesite naziv' value="${task.name}"> --%>

											<form:input type="text" class="form-control" id="name"
												path="name" placeholder='Insert name'
												value="${cinemas.name}" />
											<p class="help-block">
												<form:errors path="name" class="help-block" />
											</p>
										</div>

										<div class='form-group'>
											<label class='font-roboto'>Remark</label>
											<%-- 									<textarea class="form-control" name='remark' path='remark'  placeholder='Unesite napomenu' value="${task.remark}"></textarea> --%>
											<form:textarea type="text" class="form-control" id="remark"
												path="remark" value="${cinemas.remark}" placeholder='Enter remark'/>
											<p class="help-block">
												<form:errors path="remark" class="help-block" />
											</p>
										</div>

<%-- 										<c:out value="${model.cinemas.status}" /> --%>

<%-- 										<c:if test="${cinemas.status}"> --%>
<%-- 											<c:out value="${cinemas.code}" /> --%>
<%-- 										</c:if> --%>

										<c:set var="salary" scope="session" value="checked">
										</c:set>
										<c:set var="salary1" scope="session" value="">
										</c:set>

										<c:if test="${cinemas.status == true}">
											<c:set var="salary" scope="session" value="checked" />
											<c:set var="salary1" scope="session" value="" />
										</c:if>

										<c:if test="${cinemas.status == false}">
											<c:set var="salary1" scope="session" value="checked" />
											<c:set var="salary" scope="session" value="" />
										</c:if>

										<c:if test="${(empty salary) and (empty salary1)}">
											<c:set var="salary" scope="session" value="checked" />
										</c:if>

										<div class='form-group spacer-bottom-30'>
											<label>Status</label><br /> <label class="radio-inline">
												<!--                                     	<input type="radio" name="status"  path='status' -->
												<%--                                     	 checked  value="${task.status}" >Da   --%>
												<input type="radio" name="status" value="1" path='status'
												<c:out value="${salary}"/> />
											</label> <label class="radio-inline"> <%--                                     	<input type="radio" class="col-sm-1" name="status"  path='status' value="${task.status}">Ne --%>
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
										<input type="button" onclick="location.href='/cinemas.html';" value="Cancel"  class="btn-lg button-novi"/>

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


</body>
</html>