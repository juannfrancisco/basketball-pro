/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("TeamStandingsController", ['$scope', '$http', '$routeParams', '$timeout',
function($scope, $http, $routeParams, $timeout)
{

	$scope.flagLoading = true;
	$scope.players = [];

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;

		var request =
		$http.get( CONSTANTS.contextPath + "/teams/n/" + $routeParams.nameURL + "/championships" );
		request.success( function( response )
		{
			$scope.championships = response;
			for(var i in $scope.championships ){
			    $scope.getStandings( $scope.championships[i] );
			}
			$scope.flagLoading = false;
		} );
		request.error( function( error )
		{
		    console.log(error);
			$scope.flagLoading = false;
		});
	};


	$scope.getStandings = function( championship )
    {

        var request =
        $http.get( CONSTANTS.contextPath + "/championships/"+ championship.oid +"/teams-stats" );
        request.success( function( response )
        {
            championship.standings = response;
        } );
        request.error( function( error )
        {
            console.log(error);
        });
    };


	$scope.loadData();
}]);
