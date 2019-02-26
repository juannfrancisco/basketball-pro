/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("CourtCreateController",[ '$scope', '$http', '$location', '$routeParams',
 function( $scope, $http, $location, $routeParams )
{
	$scope.player = {};
	$scope.oid = $routeParams.id;


	$scope.save = function(){

//		NProgress.configure({ parent: '#main' });
		NProgress.start();

		var request = $http.put( CONSTANTS.contextPath + "/courts/", $scope.court );

		request.success( function( response )
		{
			NProgress.done();
			$location.path('/courts/' );

		});
		request.error( function( error )
		{
			console.log(error);
			$scope.errorMsg= "Ocurrio un error al ingresar, intente más tarde";
			$scope.diplayError = true;
			NProgress.done();
		});
	};

}]);
