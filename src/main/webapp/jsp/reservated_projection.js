var app = angular.module('myApp', ['ui.bootstrap']);

app.filter('startFrom', function() {
    return function(input, start) {
        if(input) {
            start = +start; //parse to int
            return input.slice(start);
        }
        return [];
    }
});
app.controller('dokumentCrtl', function ($scope, $http, $timeout) {
    $http.get('http://localhost:8080/getAllReservations').success(function(data){
        $scope.list = data;
        $scope.currentPage = 1; //current page
        $scope.entryLimit = 5; //max no of items to display in a page
        $scope.filteredItems = $scope.list.length; //Initially for no filter  
        $scope.totalItems = $scope.list.length;
    });
    $scope.setPage = function(pageNo) {
        $scope.currentPage = pageNo;
    };
    $scope.filter = function() {
        $timeout(function() { 
            $scope.filteredItems = $scope.filtered.length;
        }, 10);
    };
    $scope.sort_by = function(predicate) {
        $scope.predicate = predicate;
        $scope.reverse = !$scope.reverse;
    };
});

app.controller('dokumentStavkeCrtl1', function ($scope, $http, $timeout, $routeParams, $location) {

//	 $http({ url: "http://localhost:8080/dokumentstavketest1", 

        $scope.param1 = $routeParams.param1;
        $scope.param2 = $routeParams.param2;
    
	    $http({ url: "http://localhost:8080/view_dokumentStavke.html", 
		method: "get", params: { id: $routeParams.id } })
		
//		    $http({
//          method: 'GET',
//          url: 'http://localhost:8080/view_dokumentStavke.html?id=' + $routeParams.id})
		
		.then(function (data) 
				{ $scope.data = data; 
				$scope.kata = "bata";
		    	$scope.list = data;
		        $scope.currentPage = 1; //current page
		        $scope.entryLimit = 5; //max no of items to display in a page
		        $scope.filteredItems = $scope.list.length; //Initially for no filter  
		        $scope.totalItems = $scope.list.length;
				});    
   
   $scope.setPage = function(pageNo) {
       $scope.currentPage = pageNo;
   };
   $scope.filter = function() {
       $timeout(function() { 
           $scope.filteredItems = $scope.filtered.length;
       }, 10);
   };
   $scope.sort_by = function(predicate) {
       $scope.predicate = predicate;
       $scope.reverse = !$scope.reverse;
   };
});

//app.controller("dokumentController", function ($scope, $http, $routeParams) 
//
//		{ $http({ url: "view_dokumentStavke.html", 
//			method: "get", params: { id: $routeParams.id } }).then(function (response) 
//					{ $scope.data = response.data; 
//					});
//					});
//					

app.controller('dokumentStavkeCrtl', function ($scope, $http, $timeout) {
	

//	var searchObject = $location.search();
	
    $scope.dosomething = function(a)	{
    	$scope.itemid=a;
    }
    
    $scope.init = function(id)
    {
      //This function is sort of private constructor for controller
      $scope.id = id;
      $scope.idl = parselong(id);
      // $scope.name = name; 
      //Based on passed argument you can make a call to resource
      //and initialize more objects
      //$resource.getMeBond(007)
    };    
//    var searchObject = $location.search();
//    $http.get("/Angular/GetFullName?name=" + $scope.name + "&surname=" + $scope.surname)
//    $http.get('http://localhost:8080/dokumentstavke?itemid=' + $scope.idl).success(function(data){
    $http.get('http://localhost:8080/dokumentstavketest').success(function(data){
 //   	var searchObject = $location.search();
 
    	$scope.list = data;
        $scope.currentPage = 1; //current page
        $scope.entryLimit = 5; //max no of items to display in a page
        $scope.filteredItems = $scope.list.length; //Initially for no filter  
        $scope.totalItems = $scope.list.length;
    });
    
    
    $scope.setPage = function(pageNo) {
        $scope.currentPage = pageNo;
    };
    $scope.filter = function() {
        $timeout(function() { 
            $scope.filteredItems = $scope.filtered.length;
        }, 10);
    };
    $scope.sort_by = function(predicate) {
        $scope.predicate = predicate;
        $scope.reverse = !$scope.reverse;
    };
}

);

