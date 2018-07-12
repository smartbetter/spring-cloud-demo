var uiApp = angular.module("uiApp", ["ui.router"]);

uiApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("person");

    $stateProvider
        .state('person', {
            url: '/person',
            templateUrl: '/tpl/person.html',
            controller: 'PersonController'
        })
        .state('message',{
        	url:'/message',
        	 templateUrl: '/tpl/message.html',
             controller: 'MessageController'
        });
});


uiApp.controller("PersonController", function ($scope, $http) {
    $scope.people = "";
    $scope.errorMessage = "";

    $scope.getMessageResponse = function(person) {
    	$http.post('/ui/save', person).success(function(data){
            $scope.people = data;
            $scope.errorMessage = "";
        }).error(function() {
            $scope.errorMessage = "错误";
        });
    }
  
});


uiApp.controller("MessageController", function ($scope, $http) {
	$scope.str = "";
    $scope.errorMessage = "";

    $scope.getMessage = function(){
        $http.get('/ui/getMessage').success(function(data){
            $scope.str = data;
            $scope.errorMessage = "";
        }).error(function() {
            $scope.errorMessage = "错误";
        });
    }
});