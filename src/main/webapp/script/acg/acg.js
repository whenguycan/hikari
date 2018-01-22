
var app = angular.module('acg', []);
app.filter('eqs', function(){
	return function(x, y){
		return x == y;
	}
});
app.factory('http', function($http){
	return {
		post : function(url, data, callback){
			$http.post(url, data, {
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
				transformRequest : function(data){
					return $.param(data);
				}
			}).then(function(resp){
				callback(resp);
			});
		}
	}
});
app.service('http2', function($http){
	this.get = function(url, data, callback){
		url += url.indexOf('?') != -1 ? "&" : "?";
		for(var name in data){
			url += name + "=" + data[name] + "&";
		}
		$http.get(url, {}).then(function(resp){
			callback(resp);
		});
	}
});
app.controller('acg', function($scope, $http, http, http2){
	$scope.searchName = '';
	$scope.pics = [];
	$scope.searchReset = function(){
		$scope.searchName = '';
		$scope.yearSelected = '';
		$scope.serialSelected = '';
		$scope.reload();
	}
	$scope.reload = function(){
		$http.post('list.ajax', {
			s_eq_s_serialDate : $scope.yearSelected,
			sa_like_s_name : $scope.searchName,
			s_eq_i_serialState : $scope.serialSelected
		}, {
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
			transformRequest : function(data){
				return $.param(data);
			}
		}).then(function(resp){
			$scope.pics = resp.data.list;
		});
//		http.post('list.ajax?id=lepus', {}, function(resp){
//			$scope.pics = resp.data.list;
//		});
//		http2.get('list.ajax', {id:'jim'}, function(resp){
//			$scope.pics = resp.data.list;
//		});
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
	$scope.btnShow = false;
	$scope.manage = function(){
		$scope.btnShow = !$scope.btnShow;
	}
});