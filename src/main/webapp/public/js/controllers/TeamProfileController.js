/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("TeamProfileController", ['$scope', '$http', '$routeParams',
function($scope, $http, $routeParams)
{

	$scope.flagLoading = true;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
//		NProgress.configure({ parent: '#main' });
//		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/teams/n/" + $routeParams.nameURL );
		request.success( function( response )
		{
			$scope.team = response;
			$scope.flagLoading = false;
//			NProgress.done();
		} );
		request.error( function( error )
		{
		    console.log(error);
			//alert( error );
			$scope.flagLoading = false;
//			NProgress.done();
		});
	};
	$scope.loadData();
}]);
