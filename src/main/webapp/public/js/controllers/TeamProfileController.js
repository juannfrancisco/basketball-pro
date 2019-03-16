/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("TeamProfileController", ['$scope', '$http', '$routeParams',
function($scope, $http, $routeParams)
{
    $scope.nameURL = $routeParams.nameURL;

	$scope.flagLoading = true;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		var request =
		$http.get( CONSTANTS.contextPath + "/teams/n/" + $routeParams.nameURL + "/last-match" );
		request.success( function( response )
		{
			$scope.match = response;
			$scope.flagLoading = false;
		} );
		request.error( function( error )
		{
		    console.log(error);
			$scope.flagLoading = false;
		});
	};
	$scope.loadData();
}]);
