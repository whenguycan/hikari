
var app = angular.module('anime', []);
app.controller('anime', function($scope, $http){
	$scope.editShow = true;
	$scope.delShow = false;
	$scope.count1 = 1;
	$scope.$on('toggle', function(e, data){
		$scope.delShow = !$scope.delShow;
	});
	$scope.years = {};
	$scope.reloadYears = function(){
		$http.post('ng/years.ajax', {}, {}).then(function(resp){
			console.log(resp.data);
			$scope.years = resp.data;
		});
	}
	$scope.currYear = function(year){
		console.log(year);
		console.log($scope.count1);
		$scope.count1++;
		return year == $scope.yearSelect;
	}
	$scope.reloadYears();
	$scope.yearSelect = '';
	$scope.yearClick = function(year){
		$scope.yearSelect = year;
		/*reloadYears();*/
	}
	
});

function cur(){
	
};