/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("ChampionshipController",[ '$scope','$http','$location', 'GenericService',
function($scope, $http, $location, GenericService){

	$scope.title = "Campeonatos";
	$scope.nameObject = "championship";
	$scope.nameObjects = "championships";

	$scope.objects = [];
	$scope.flagLoading = false;
	$scope.flagErrorLoading = false;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		NProgress.start();

		GenericService.getAll( $scope.nameObjects ).then(function(data) {
			$scope.objects = data;
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
