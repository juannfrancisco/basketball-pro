var app = angular.module('AppBasketPlayer', ['ui.bootstrap','ngRoute']);
var CONSTANTS = { contextPath: "/api/v1" };

app.config(['$routeProvider', '$controllerProvider',function($routeProvider, $controllerProvider)
{
	app.registerCtrl = $controllerProvider.register;
	$routeProvider
    .when('/',{controller:'DashboardController',templateUrl: 'inicio.html'})
    .when('/:id',{controller:'TeamRosterProfileController',templateUrl: '/public/pages/players/player-profile.html'})
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
