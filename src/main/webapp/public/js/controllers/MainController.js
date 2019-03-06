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
        var url= $location.url().split("/")[2];
        $scope.flagLoading = true;
        var request =
        $http.get( CONSTANTS.contextPath + "/teams/n/" + url );
        request.success( function( response )
        {
            $scope.team = response;
            $scope.flagLoading = false;
        } );
        request.error( function( error )
        {
            console.log(error);
            $scope.flagLoading = false;
        });
    };

    $scope.loadPlayerData = function(  ){

        var url= $location.url().split("/");
        if(url[3] == "roster" &&  url[4]){

            $scope.flagLoading = true;
            var request =
            $http.get( CONSTANTS.contextPath + "/players/" + url[4] );
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
        }

    }


    $scope.loadPlayerData();
    $scope.loadData();
}]);
