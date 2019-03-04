/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("GameProfileController", ['$scope', '$http', '$routeParams',
function($scope, $http, $routeParams)
{

	$scope.flagLoading = true;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;

		var request =
		$http.get( CONSTANTS.contextPath + "/matches/" + $routeParams.id );
		request.success( function( response )
		{
			$scope.match = response;
			$scope.loadStatsData();
			$scope.flagLoading = false;
		} );
		request.error( function( error )
		{
		    console.log(error);
			$scope.flagLoading = false;
		});
	};

	$scope.loadStatsData = function(  )
    {
        $scope.flagLoading = true;

        var request =
        $http.get( CONSTANTS.contextPath + "/matches/" + $routeParams.id + "/stats" );
        request.success( function( response )
        {
            $scope.stats = response;
            $scope.flagLoading = false;
            $scope.calculateStats();
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.flagLoading = false;
        });
    };


    /**
    *
    **/
    $scope.calculateStats = function(){
        debugger;
        var pointsLocal = 0;
        for (var i in $scope.stats){
            if( $scope.stats[i].typeTeam == 'LOCAL' &&  $scope.stats[i].type == "PTS"){
                pointsLocal = pointsLocal + $scope.stats[i].value;
            }
        }
        $scope.match.scoreLocal = pointsLocal;
    }

	$scope.loadData();

}]);
