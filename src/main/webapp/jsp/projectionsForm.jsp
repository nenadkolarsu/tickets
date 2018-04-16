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

									<form:form method="POST" action="save_projections.html"
										modelAttribute="projections">

										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />

										<form:input type="hidden" name="id" path="id"
											value="${projections.id}" />
											
										<div class="form-group"${error != null ? 'has-error' : ''}'>
											<label class='font-awesome'>Theatre</label>

											<form:select path="theatres.id" class="form-control">
<%-- 												<form:option value="0" label="--- Select ---" /> --%>
												<form:options items="${eTheatres}" />
											</form:select>

											<p class="help-block">
												<form:errors path="theatres.id" class="help-block" />
											</p>
										</div>											

										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-oxygen'>Code</label>

											<form:input type="text" class="form-control " name="code"
												path="code" value="${projections.code}" autofocus="true"
												placeholder='Insert code' />

											<p class="help-block">
												<form:errors path="code" class="help-block text-danger" />
											</p>
										</div>

										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-awesome'>Date projection</label>

											<form:input type="date" dateformat="d M y" class="form-control" id="projection_date"

												path="projection_date" placeholder='Date projection'
												value="${projections.projection_date}" />
											<p class="help-block">
												<form:errors path="projection_date" class="help-block" />
											</p>
										</div>
										
										<div class='form-group ${error != null ? 'has-error' : ''}'>
											<label class='font-awesome'>Projection time </label>

											<form:input type="time" class="form-control" id="projection_time"

												path="projection_time" placeholder='Time projection'
												value="${projections.projection_time}" />
											<p class="help-block">
												<form:errors path="projection_time" class="help-block" />
											</p>
										</div>
										
<!-- 										Note: <input> elements with type="datetime-local" do not show as any datetime field/calendar in Firefox or in IE12 and earlier versions. -->
<%-- 										<div class='form-group ${error != null ? 'has-error' : ''}'> --%>
<!-- 											<label class='font-awesome'>Projection date-time </label> -->

<%-- 											<form:input type="datetime-local" class="form-control" id="projection_datetime" --%>

<%-- 												path="projection_datetime" placeholder='Datetime projection' --%>
<%-- 												value="${projections.projection_datetime}" /> --%>
<!-- 											<p class="help-block"> -->
<%-- 												<form:errors path="projection_datetime" class="help-block" /> --%>
<!-- 											</p> -->
<!-- 										</div> -->
										
										<div class="form-group"${error != null ? 'has-error' : ''}'>
											<label class='font-awesome'>Movie</label>

											<form:select path="movies.id" class="form-control">
<%-- 												<form:option value="0" label="--- Select ---" /> --%>
												<form:options items="${eMovies}" />
											</form:select>

											<p class="help-block">
												<form:errors path="movies.id" class="help-block" />
											</p>
										</div>		
										
										<div class='form-group'>
											<label class='font-roboto'>Remark</label>
											<form:textarea type="text" class="form-control" id="remark"
												path="remark" value="${projections.remark}" placeholder='Enter remark'/>
											<p class="help-block">
												<form:errors path="remark" class="help-block" />
											</p>
										</div>

<%-- 										<c:out value="${model.projections.status}" /> --%>

<%-- 										<c:if test="${projections.status}"> --%>
<%-- 											<c:out value="${projections.code}" /> --%>
<%-- 										</c:if> --%>

										<c:set var="salary" scope="session" value="checked">
										</c:set>
										<c:set var="salary1" scope="session" value="">
										</c:set>

										<c:if test="${projections.status == true}">
											<c:set var="salary" scope="session" value="checked" />
											<c:set var="salary1" scope="session" value="" />
										</c:if>

										<c:if test="${projections.status == false}">
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
										<input type="button" onclick="location.href='/projections.html';" value="Cancel"  class="btn-lg button-novi"/>

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