/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("PlayerEditController", ['$scope', '$http', '$routeParams', '$location',
function($scope, $http, $routeParams, $location)
{
	$scope.flagLoading = true;
	$scope.flagErrorLoading = false;
	$scope.player = {};
	$scope.button = {};

	$scope.loadData = function( )
	{
		$scope.flagLoading = true;
//		NProgress.configure({ parent: '#main' });
		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/players/" + $routeParams.idPlayer );
		request.success( function( response )
		{
			$scope.player = response;
			$scope.player.birthdate_ = new Date($scope.player.birthdate);
			if( $scope.player.gender == 'FEMALE' ){
                $scope.button.female = 'btn-primary';
            }else{
                $scope.button.male = 'btn-primary';
            }
			$scope.flagLoading = false;
			NProgress.done();
		} );
		request.error( function( error )
		{
			$scope.flagErrorLoading = true;
			$scope.flagLoading = false;
			NProgress.done();
		});
	};


	$scope.save = function(){

        delete $scope.player.birthdate_;
	    var request =
    		$http.post( CONSTANTS.contextPath + "/teams/" + $routeParams.id + "/players/"+ $routeParams.idPlayer, $scope.player );
    	request.success( function( response )
        {
            $scope.flagLoading = false;
            NProgress.done();
            $location.path( "/team/"+$routeParams.id+"/players" );

        } );
        request.error( function( error )
        {
            alert("Ocurrio un error al guardar");
            $scope.flagErrorLoading = true;
            $scope.flagLoading = false;
            NProgress.done();
        });
	}


	$scope.loadData();



}]);
