/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("TeamPlayersController", ['$scope', '$http', '$routeParams',
function($scope, $http, $routeParams)
{
	$scope.flagLoading = true;

	$scope.teamOid = $routeParams.id;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
//		NProgress.configure({ parent: '#main' });
		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/teams/" + $routeParams.id );
		request.success( function( response )
		{
			$scope.team = response;
//			$scope.team.players = players; //TODO: REMOVE
//			$scope.team.coach = coach;//TODO: REMOVE

			$scope.flagLoading = false;
			NProgress.done();
		} );
		request.error( function( error )
		{
			alert( error );
			$scope.flagLoading = false;
			NProgress.done();
		});
	};


	/**
     *
     */
    $scope.deletePlayer = function( player ){
        var request = $http.delete( CONSTANTS.contextPath + "/teams/"+ $routeParams.id + "/players/" +  player.oid );
        request.success( function( response )
        {
            NProgress.done();
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.errorMsg= "Ocurrio un error al ingresar el equipo, intente más tarde";
            $scope.diplayError = true;
            NProgress.done();
        });


        for( i in $scope.team.players ){
            if( $scope.team.players[i].oid === player.oid ){
                $scope.team.players.splice(i , 1);
                break;
            }
        }
    };


	$scope.loadData();



}]);
