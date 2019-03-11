/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("MainController", [ '$scope', '$http', '$location','$routeParams', '$location',
function( $scope, $http, $location,$routeParams, $location )
{
	/**
	 *
	 */
	$scope.ref = function( link ){
		$location.url( link );
	};


	/**
	 *
	 */
	$scope.msgI18n = function( Prop ){
		return Language[Prop];
	};


	$scope.flagLoading = true;

    $scope.loadData = function(  )
    {
        var url= $location.url().split("/")[1];
        debugger;
        $scope.flagLoading = true;
        var request =
        $http.get( CONSTANTS.contextPath + "/players/" + url );
        request.success( function( response )
        {
            $scope.player = response;
            $scope.flagLoading = false;
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.flagLoading = false;
        });
    };

    $scope.loadData();
}]);
