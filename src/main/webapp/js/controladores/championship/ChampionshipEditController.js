/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("ChampionshipEditController", [ '$scope', '$http', '$routeParams', 'GenericService',
function($scope, $http, $routeParams, GenericService)
{
	$scope.title = "Campeonatos";
	$scope.nameObject = "championship";
	$scope.nameObjects = "championships";


	$scope.flagLoading = false;
	$scope.flagErrorLoading = false;

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


    $scope.addTeam = function(  )
	{
		$scope.flagLoading = true;
		NProgress.start();
        var request = $http.put( CONSTANTS.contextPath + "/championships/"+ $routeParams.id + "/teams", {oid:$scope.teamOid} );
        request.success( function( response )
        {
            $scope.teamOid = "";
            console.log( response );
            $scope.flagLoading = false;
            NProgress.done();
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.errorMsg= "Ocurrio un error al ingresar el equipo, intente más tarde";
            $scope.diplayError = true;
            NProgress.done();
        });

	};


	$scope.allTeams = function(  )
    {
        $scope.flagLoading = true;
        NProgress.start();
        var request = $http.get( CONSTANTS.contextPath + "/teams");
        request.success( function( response )
        {
            $scope.teams = response;
            console.log( response );
            NProgress.done();
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.errorMsg= "Ocurrio un error al ingresar el equipo, intente más tarde";
            $scope.diplayError = true;
            NProgress.done();
        });

    };

    $scope.allTeams();
	$scope.loadData();
}]);
