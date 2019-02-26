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
		var birthdatex = new Date(birthdate);
		var date = new Date();
		return date.getFullYear() - birthdatex.getFullYear();
	};


	$scope.loadData();



}]);
