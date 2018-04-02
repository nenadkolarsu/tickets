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

											<form:input type="date" class="form-control" id="projection_date"

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
										
<!-- 									<input style='width:80px' required name='hora' id='hora' type="time" min="08:00:00" max="20:00:00"/> -->

<%-- 										<fmt:formatDate value="${projections.projection_datetime}" pattern="yyyy-MM-dd hh:mm" var="projection_datetime"/> --%>
<%-- 										<form:input path="date" value="${projections.projection_datetime}"/> --%>
										
<!-- 										    <input id="party" type="datetime-local" name="partydate" -->
<!--            min="2017-06-01T08:30" max="2017-06-30T16:30" -->
<!--            pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}" required> -->

<!--                <input id="reserve" type="time" name="appt-time" -->
<!--            min="12:00" max="18:00" required -->
<!--            pattern="[0-9]{2}:[0-9]{2}"> -->
           
<%--            								<div class='form-group ${error != null ? 'has-error' : ''}'> --%>
<!-- 											<label class='font-awesome'>Reserve</label> -->

<%-- 											<form:input type="time" class="form-control" id="reserve" --%>
<%-- 												path="reserve" placeholder='Insert time in format 00:00' pattern="[0-9]{2}:[0-9]{2}" --%>
<%-- 												value="${projections.reserve}" /> --%>
<!-- 											<p class="help-block"> -->
<%-- 												<form:errors path="reserve" class="help-block" /> --%>
<!-- 											</p> -->
<!-- 										</div> -->
										
<%-- 										<div class='form-group ${error != null ? 'has-error' : ''}'> --%>
<!-- 											<label class='font-awesome'>Movie</label> -->

<%-- 											<form:input type="text" class="form-control" id="movie" --%>
<%-- 												path="movie" placeholder='Insert movie' --%>
<%-- 												value="${projections.movie}" /> --%>
<!-- 											<p class="help-block"> -->
<%-- 												<form:errors path="movie" class="help-block" /> --%>
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
											<%-- 									<textarea class="form-control" name='remark' path='remark'  placeholder='Unesite napomenu' value="${task.remark}"></textarea> --%>
											<form:textarea type="text" class="form-control" id="remark"
												path="remark" value="${projections.remark}" placeholder='Enter remark'/>
											<p class="help-block">
												<form:errors path="remark" class="help-block" />
											</p>
										</div>

										<c:out value="${model.projections.status}" />

										<c:if test="${projections.status}">
											<c:out value="${projections.code}" />
										</c:if>

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
										<input type="button" onclick="location.href='/';" value="Cancel"  class="btn-lg button-novi"/>
<%-- 										<input type="button" onclick="location.href='dokumentstavkefinal.html?page=0&id=${dokumentStavke.idDokument.id}';" value="Cancel"  class="btn-lg button-novi"/> --%>

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