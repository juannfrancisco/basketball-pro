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

			$scope.loadTeams();
			$scope.loadMatches();
        })
        .catch(function(err) {
        	$scope.flagLoading = false;
			$scope.flagErrorLoading = true;
			NProgress.done();
        });
	};


	/**
     *
     */
    $scope.loadTeams = function(  )
    {
        $scope.flagLoading = true;
        NProgress.start();
        var request = $http.get( CONSTANTS.contextPath + "/"+$scope.nameObjects + "/"+ $routeParams.id +"/teams-stats");
        request.success( function( response )
        {
            $scope.flagLoading = false;
            $scope.teamsStats = response;
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

    /**
     *
     */
    $scope.loadMatches = function(  )
    {
        $scope.flagLoading = true;
        NProgress.start();
        var request = $http.get( CONSTANTS.contextPath + "/"+$scope.nameObjects + "/"+ $routeParams.id +"/matches");
        request.success( function( response )
        {
            $scope.flagLoading = false;
            $scope.matches = response;
            console.log( response );
            NProgress.done();
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.errorMsg= "Ocurrio un error al cargar los equipos, intente más tarde";
            $scope.diplayError = true;
            NProgress.done();
        });
    };


	$scope.loadData();
}]);
