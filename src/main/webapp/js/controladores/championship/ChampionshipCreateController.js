/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("ChampionshipCreateController",['$scope', '$http', '$location', 'GenericService',
function( $scope, $http, $location, GenericService )
{
	$scope.title = "Campeonato";
	$scope.nameObject = "championship";
	$scope.nameObjects = "championships";

	/**
	 *
	 */
	$scope.save = function(){
		NProgress.start();

		GenericService.save($scope.nameObjects, $scope.object).then(function(data) {
			NProgress.done();
			$location.path('/'+$scope.nameObjects+'/' );
        })
        .catch(function(err) {
        	$scope.errorMsg= "Ocurrio un error al ingresar, intente más tarde";
			$scope.diplayError = true;
			NProgress.done();
        });
	};

}]);
