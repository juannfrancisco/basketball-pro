/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("TeamEditController", ['$scope', '$http', '$routeParams',function($scope, $http, $routeParams)
{

	var coach = { name:'Fred', lastName:'Hoiberg', birthdate:'12 de Enero 1989' };
	$scope.flagLoading = false;
	$scope.flagErrorLoading = false;

	/**
	 *
	 */
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
			$scope.team.coach = coach;//TODO: REMOVE
			$scope.flagLoading = false;
			NProgress.done();

			setTimeout(function(){

				$(".list-group-item.edit").click( function(){
					$(".list-group-item .actions").each( function(){ $(this).hide(); } );
					$(".list-group-item .badge").each( function(){ $(this).show(); } );
					$(this).find( "div.actions" ).show();
					$(this).find( "span.badge" ).hide();
				} );

			}, 1000);




		} );
		request.error( function( error )
		{
			$scope.flagLoading = false;
			$scope.flagErrorLoading = true;
			NProgress.done();
		});
	};



	/**
	 *
	 */
	$scope.deletePlayer = function( player ){

		if( $routeParams.id )
			var request = $http.delete( CONSTANTS.contextPath + "/teams/"+ $routeParams.id + "/players/" +  player.oid );
		else
			var request = $http.delete( CONSTANTS.contextPath + "/players/" + player.oid  );

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



	/**
     *
     */
    $scope.deleteTeam = function( ){

        //TODO: Agregar una alerta antes de eliminar
        NProgress.start();
        var request = $http.delete( CONSTANTS.contextPath + "/teams/"+ $routeParams.id );
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
    };


    /**
     *
     */
    $scope.updateTeam = function( ){

        //TODO: Agregar una alerta antes de eliminar
        if($scope.team.coach){
            $scope.team.coach = {oid: $scope.team.coach.oid};
        }
        console.log($scope.team);
        NProgress.start();
        var request = $http.post( CONSTANTS.contextPath + "/teams/"+ $routeParams.id, $scope.team );
        request.success( function( response )
        {
            NProgress.done();
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.errorMsg= "Ocurrio un error al actualizar el equipo, intente más tarde";
            $scope.diplayError = true;
            NProgress.done();
        });
    };



	$scope.loadData();
}]);
