/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("CourtController", ['$scope', '$http', '$location', 'GenericService',
function($scope, $http, $location, GenericService){
	
	$scope.players = [];
	$scope.flagLoading = false;
	$scope.flagErrorLoading = false;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		NProgress.start();

		GenericService.getAll("courts").then(function(data) {
			$scope.courts = data;
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
