/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("ChampionshipProfileController", ['$scope', '$http', '$routeParams', 'GenericService', function($scope, $http, $routeParams, GenericService)
{
	$scope.nameObject = "championship";
	$scope.nameObjects = "championships";

	$scope.flagLoading = true;
	$scope.flagErrorLoading = false;
	$scope.object = {};



	/**
	 *
	 */
	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		NProgress.start();
		GenericService.getById( $scope.nameObjects, $routeParams.id ).then(function(data) {
			$scope.object = data;
			$scope.flagLoading = false;
			NProgress.done();
        })
        .catch(function(err) {
        	$scope.flagLoading = false;
			$scope.flagErrorLoading = true;
			NProgress.done();
        });
	};

	$scope.loadData();
}]);
