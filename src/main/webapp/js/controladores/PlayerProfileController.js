/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("PlayerProfileController", ['$scope', '$http', '$routeParams',
 function($scope, $http, $routeParams)
{
	$scope.flagLoading = true;
	$scope.flagErrorLoading = false;
	$scope.player = {};

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
//		NProgress.configure({ parent: '#main' });
		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/players/" + $routeParams.id );
		request.success( function( response )
		{
			$scope.player = response;
			$scope.player.age = $scope.getAge( $scope.player.birthdate );
			$scope.flagLoading = false;
			NProgress.done();
		} );
		request.error( function( error )
		{
			$scope.flagErrorLoading = true;
			$scope.flagLoading = false;
			NProgress.done();
		});
	};
	
	$scope.getAge = function( birthdate ){
	    //SAFARI - 1989-11-01T03:00:00.000+0000
	    //CHROME - 1989-11-01T03:00:00.000+0000

		var birthdatex = new Date(birthdate.substring(0,10));
		var date = new Date();
		return date.getFullYear() - birthdatex.getFullYear();
	};


	$scope.loadData();



}]);
