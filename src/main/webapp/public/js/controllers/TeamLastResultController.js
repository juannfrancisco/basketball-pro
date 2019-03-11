/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("TeamLastResultController", ['$scope', '$http', '$routeParams', '$timeout', '$location',
function($scope, $http, $routeParams, $timeout,$location)
{
    /**
	 *
	 */
	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		var request =
		$http.get( CONSTANTS.contextPath + "/teams/n/"+$routeParams.nameURL+"/matches" );
		request.success( function( response )
		{
			$scope.matches = response;
			for(i in $scope.matches ){

			    if( $scope.matches[i].local.nameURL == $routeParams.nameURL ){
                    $scope.matches[i].status = "Local";
                    $scope.matches[i].versus = $scope.matches[i].visitor;
                    $scope.matches[i].result = $scope.matches[i].local > $scope.matches[i].visitor?'W':'L';
			    }else{
			        $scope.matches[i].status = "Visita";
			        $scope.matches[i].versus = $scope.matches[i].local;
			        $scope.matches[i].result = $scope.matches[i].visitor > $scope.matches[i].local?'W':'L';
			    }
			}
			$scope.flagLoading = false;
		} );
		request.error( function( error )
		{
			$scope.flagLoading = false;
			$scope.flagErrorLoading = true;
		});
	};


	$scope.goGame= function(match){
        $location.path( '/games/'+match.oid );
	}


	$scope.loadData();
}]);
