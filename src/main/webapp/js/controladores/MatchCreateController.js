/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador
 *************************************************************/
app.controller("MatchCreateController", ['$scope', '$http', '$location' , 'GenericService', '$routeParams',
function($scope, $http, $location , GenericService, $routeParams ){

	$scope.match = {};
	$scope.teams = [];
	$scope.flagLoading = false;
	$scope.flagErrorLoading = false;
	$scope.dateOptions = {
			formatYear: 'yy',
		    maxDate: new Date(2020, 5, 22),
		    minDate: new Date(),
		    startingDay: 1
	};



	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		NProgress.start();

		GenericService.getAll("courts").then(function(data){
			$scope.courts = data;
		}).then(  GenericService.getAll("teams").then(function(data){
			$scope.teams = data;
			$scope.flagLoading = false;
			NProgress.done();
		}));


	};



	$scope.save = function(){
		NProgress.start();

		$scope.match.championship = {oid: $routeParams.id };

		var request = $http.put( CONSTANTS.contextPath + "/matches", $scope.match );
		request.success( function( response )
		{
			console.log( response );
			NProgress.done();
			$location.path('/match/'+ response.oid);
		} );
		request.error( function( error )
		{
			console.log(error);
			$scope.errorMsg= "Ocurrio un error al ingresar el equipo, intente más tarde";
			$scope.diplayError = true;
			NProgress.done();
		});
	};

	$scope.loadData();


}]);
