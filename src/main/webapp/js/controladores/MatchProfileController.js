/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("MatchProfileController",['$scope', '$http', '$routeParams', function($scope, $http, $routeParams)
{
	$scope.flagLoading = true;

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
//		NProgress.configure({ parent: '#main' });
		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/matches/" + $routeParams.id );
		request.success( function( response )
		{
			$scope.match = response;
			$scope.match.date = new Date( $scope.match.date );
			$scope.flagLoading = false;
			NProgress.done();

			$scope.loadStatsData();
		} );
		request.error( function( error )
		{
			//alert( error );
			$scope.flagLoading = false;
			NProgress.done();
		});
	};


	$scope.loadStatsData = function(  )
    {
        $scope.flagLoading = true;
        NProgress.start();

        var request =
        $http.get( CONSTANTS.contextPath + "/matches/" + $routeParams.id + "/stats");
        request.success( function( response )
        {
            $scope.stats = response;
            $scope.flagLoading = false;
            NProgress.done();
        } );
        request.error( function( error )
        {
            $scope.flagLoading = false;
            NProgress.done();
        });
    };


	$scope.loadData();



}]);
