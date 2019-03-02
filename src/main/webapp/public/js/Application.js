var app = angular.module('AppBasket', ['ui.bootstrap','ngRoute']);
var CONSTANTS = { contextPath: "/api/v1" };

app.config(['$routeProvider', '$controllerProvider',function($routeProvider, $controllerProvider)
{
	app.registerCtrl = $controllerProvider.register;
	$routeProvider
    .when('/',{controller:'DashboardController',templateUrl: 'inicio.html'})
    .when('/team/:nameURL',{controller:'TeamProfileController',templateUrl: 'pages/team-profile.html'})
    .when('/team/:nameURL/roster',{controller:'TeamRosterController',templateUrl: 'pages/roster.html'})
    .when('/team/:nameURL/standings',{controller:'TeamStandingsController',templateUrl: 'pages/team-standings.html'})
    .when('/teams',{controller:'TeamController',templateUrl: 'pages/teams/teams.html'})
    .otherwise({redirectTo:'/'});
}]);

/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador
 *************************************************************/
app.controller("DashboardController",['$scope','$location', function($scope, $location)
{
	$scope.ref = function( link ){
		$location.url( link );
	};
}]);
