var app = angular.module('App', ['ui.bootstrap','ngRoute']);


app.config(['$routeProvider', '$controllerProvider',function($routeProvider, $controllerProvider)
{
	app.registerCtrl = $controllerProvider.register;
	$routeProvider
    .when('/',
    {
    	controller:'DashboardController',
    	templateUrl: 'inicio.html'
    })

    .when('/teams',{controller:'TeamController',templateUrl: 'pages/teams/teams.html'})
    .when('/team/create',{controller:'TeamCreateController',templateUrl:  'pages/teams/team-create.html'})
    .when('/team/:id',{controller:'TeamProfileController',templateUrl:  'pages/teams/team-profile.html'})
    .when('/team/edit/:id',{controller:'TeamEditController',templateUrl:  'pages/teams/team-edit.html'})
    .when('/team/edit/:id/photo',{controller:'TeamEditController',templateUrl:  'pages/teams/team-edit-photo.html'})
    .when('/team/:id/players',{controller:'TeamPlayersController',templateUrl:  'pages/players/team-players.html'})
    .when('/team/:id/players/add',{controller:'PlayerCreateController',templateUrl:  'pages/teams/team-add-player.html'})
    .when('/team/:id/players/:idPlayer/edit',{controller:'PlayerEditController',templateUrl:  'pages/teams/team-edit-player.html'})

    .when('/player/:id',{controller:'PlayerProfileController',templateUrl:  'pages/players/player-profile.html'})
    .when('/players',{controller:'PlayerController',templateUrl:  'pages/players/players.html'})
    .when('/players/create',{controller:'PlayerCreateController',templateUrl:  'pages/teams/team-add-player.html'})


    .when('/court/:id',{controller:'CourtProfileController',templateUrl:  'pages/courts/court-profile.html'})
    .when('/courts',{controller:'CourtController',templateUrl:  'pages/courts/courts.html'})
    .when('/courts/create',{controller:'CourtCreateController',templateUrl:  'pages/courts/court-create.html'})
    .when('/court/edit/:id',{controller:'CourtEditController',templateUrl:  'pages/courts/court-edit.html'})

    .when('/championship/:id',{controller:'ChampionshipProfileController',templateUrl:  'pages/championships/championship-profile.html'})
    .when('/championships',{controller:'ChampionshipController',templateUrl:  'pages/championships/championships.html'})
    .when('/championships/create',{controller:'ChampionshipCreateController',templateUrl:  'pages/championships/championship-create.html'})
    .when('/championship/edit/:id',{controller:'ChampionshipEditController',templateUrl:  'pages/championships/championship-edit.html'})


    .when('/matches',{controller:'MatchController',templateUrl:  'pages/matches/matches.html'})
    .when('/match/:id',{controller:'MatchProfileController',templateUrl:  'pages/matches/match-profile.html'})
    .when('/match/:id/playing',{controller:'MatchPlayingController',templateUrl:  'pages/matches/match-playing.html'})
    .when('/matches/create',{controller:'MatchCreateController',templateUrl:  'pages/matches/match-create.html'})

    .when('/administracion/usuarios',{controller:'UsuarioController',templateUrl:  'pages/administracion/usuarios/usuarios.jsp'})
    .otherwise(
    {
    	redirectTo:'/'
    });

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
