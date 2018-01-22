
var app = angular.module('acg', []);
app.filter('eqs', function(){
	return function(x, y){
		return x == y;
	}
});
app.factory('factory', function(){
	return {
		get : function(){
			console.log(Math.random());
		}
	};
});
app.controller('acg', function($scope, $http){
	$scope.searchName = '';
	$scope.pics = [];
	$scope.searchReset = function(){
		$scope.searchName = '';
		$scope.yearSelected = '';
		$scope.serialSelected = '';
		$scope.reload();
	}
	$scope.reload = function(){
		var url = 'list.ajax?s_eq_s_serialDate=' + $scope.yearSelected + "&sa_like_s_name=" + $scope.searchName + "&s_eq_i_serialState=" + $scope.serialSelected;
		$http.post(url, {}, {}).then(function(resp){
			$scope.pics = resp.data.list;
		});
	}
	$scope.yearSelected = '';
	$scope.years = [];
	$http.post('years.ajax').then(function(resp){
		$scope.years = resp.data;
		$scope.yearSelected = $scope.years[0].name;
	});
	$scope.serialSelected = '';
	$scope.serials = [];
	$http.post('serials.ajax').then(function(resp){
		$scope.serials = resp.data;
	});
	$scope.reload();
	$scope.yearChange = function(year){
		$scope.yearSelected = year;
		$scope.reload();
	}
	$scope.serialChange = function(serial){
		$scope.serialSelected = serial;
		$scope.reload();
	}
	$scope.btnShow = true ;
	$scope.manage = function(){
		$scope.btnShow = !$scope.btnShow;
	}
});