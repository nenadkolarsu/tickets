<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="top.jsp"%>

	<div id="wrapper" ng-app="myApp">

		<div id="page-wrapper">
			<div class='row'>

				<div class='col-lg-12 '>
					<div class='panel panel-default margin-top-10 border-radius-0'>

						<div class='panel-heading'>${title} from date ${currentDate}</div>

						<div class='panel-body'>
						
<!-- 							<div class='row spacer-bottom'> -->
<!-- 								<div class='col-lg-12'> -->
<%-- 									<a href="${new_item}"> --%>
<!-- 										<button -->
<!-- 											class='btn btn-lg btn-success button-novi border-radius-0'> -->
<!-- 											<span class='glyphicon glyphicon-file'></span> New -->
<!-- 										</button> -->
<%-- 									</a> <a href="${print_item}" target="_blank"> --%>
<!-- 										<button -->
<!-- 											class='btn btn-lg btn-success button-novi border-radius-0 margin-left-10'> -->
<!-- 											<span class='fa fa-print'></span> Print -->
<!-- 										</button> -->
<!-- 										            <div class="col-md-12"> -->
<!-- 					                <h4>{{error}}</h4>             		 -->
<!--           											 </div> -->
<!-- 									</a> -->
<!-- 								</div> -->
<!-- 							</div> -->



<div class='row'>
<div class='col-lg-12' ng-controller="dokumentCrtl">

<!-- <br/> -->
<!-- <blockquote><h4><a href="http://angularcode.com/angularjs-datagrid-paging-sorting-filter-using-php-and-mysql/">Simple Datagrid with search, sort and paging using AngularJS + PHP + MySQL</a></h4></blockquote> -->
<!-- <br/> -->

    <div class="row">
        <div class="col-md-2">PageSize:
            <select ng-model="entryLimit" class="form-control">
                <option>5</option>
                <option>10</option>
                <option>20</option>
                <option>50</option>
                <option>100</option>
            </select>
        </div>
        <div class="col-md-3">Filter:
            <input type="text" ng-model="search" ng-change="filter()" placeholder="Filter" class="form-control" />
        </div>
        <div class="col-md-4">
            <h5>Filtered {{ filtered.length }} of {{ totalItems}} total theatres</h5>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-md-12" ng-show="filteredItems > 0">
             <table class="display table table-striped table-bordered table-hover border-radius-0">
            <thead>
            <th style="width: 7%;">Projection Id&nbsp;<a ng-click="sort_by('id');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 15%;">Cinemas&nbsp;<a ng-click="sort_by('id_cinema');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 15%;">Theatres&nbsp;<a ng-click="sort_by('id_theatre');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 6%;">Projection Code&nbsp;<a ng-click="sort_by('code');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 20%;">Movie&nbsp;<a ng-click="sort_by('id_movie');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 7%;">Date &nbsp;<a ng-click="sort_by('data.projection_date');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 7%;">Time&nbsp;<a ng-click="sort_by('projection_date');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>         
            <th style="width: 6%;">Status&nbsp;<a ng-click="sort_by('status');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
            <th style="width: 11%;">Action&nbsp;<a ng-click="sort_by('status');"><i class="glyphicon glyphicon-sort sort-arrows"></i></a></th>
           </thead>
            <tbody>
                <tr ng-repeat="data in filtered = (list | filter:search | orderBy : predicate :reverse) | startFrom:(currentPage-1)*entryLimit | limitTo:entryLimit">
                    <td>{{data.id}}</td>
                    <td>{{data.theatres.cinemas.name}}</td>
                    <td>{{data.theatres.name}}</td>
                    <td>{{data.code}}</td>
                    <td>{{data.movies.name}}</td>
                    <td>{{data.projection_date | date:"dd.MM.yyyy"}}</td>
                    <td>{{data.projection_time | date:"HH:mm:ss"}}</td>
                    <td>{{data.status}}</td>
                    
                    <td>
<!--                         <a href="update_projection.html?id={{data.id}}"><i class="glyphicon glyphicon-file"></i></a>  -->
<!--                         <a href="delete_projection.html?id={{data.id}}" onClick="return ConfirmDelete();"><i class="glyphicon glyphicon-trash"></i></a> -->

                        <a href="projection_reservation.html?id={{data.id}}"><i class="glyphicon glyphicon-phone-alt"></i>&nbsp Reserve</a>
                        
                         
                    </td>
                    
                </tr>
            </tbody>
            </table>
        </div>
        <div class="col-md-12" ng-show="filteredItems == 0">
            <div class="col-md-12">
                <h4>No available projections found</h4>             		
            </div>

        </div>
        <div class="col-md-12" ng-show="filteredItems > 0">    
            <div pagination="" page="currentPage" on-select-page="setPage(page)" boundary-links="true" total-items="filteredItems" items-per-page="entryLimit" class="pagination-small" previous-text="&laquo;" next-text="&raquo;"></div>
            
            
        </div>
    </div>
</div>
</div>   
<!-- controller -->


<!-- second controller  -->

  
<!-- second controller -->


</div></div></div></div></div></div>

<!-- <div class="container"> -->

  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Help</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Reserve</h4>
        </div>
        <div class="modal-body">
          <p>Review of available projections from today's date  </p>
          <p>Click on telephone icon and make a reservation for the projection. </p>
          
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
<!-- </div> -->


<script src="https://code.angularjs.org/1.2.32/angular.js"></script>
<script src="jsp/ui-bootstrap-tpls-0.10.0.min.js"></script>
<script src="jsp/availableprojections.js"></script>


<script>
		function ConfirmDelete() {
			var x = confirm("Are you sure you want to delete?");
			if (x)
				return true;
			else
				return false;
		}
	</script>         
    </body>
</html>
