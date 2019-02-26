/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador PerfilEquipoController
 *************************************************************/
app.controller("CourtProfileController",['$scope', '$http', '$routeParams',
 function($scope, $http, $routeParams)
{
	$scope.flagLoading = true;
	$scope.flagErrorLoading = false;
	$scope.court = {};

	$scope.loadData = function(  )
	{
		$scope.flagLoading = true;
		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/courts/" + $routeParams.id );
		request.success( function( response )
		{
			$scope.court = response;
			$scope.flagLoading = false;
			initMap();
			NProgress.done();
		} );
		request.error( function( error )
		{
			$scope.flagErrorLoading = true;
			$scope.flagLoading = false;
			NProgress.done();
		});
	};


	$scope.loadData();


	function initMap() {

		var location = {lat: $scope.court.location.latitude, lng:  $scope.court.location.longitude };
        var map = new google.maps.Map(document.getElementById('map'), {
          center: location,
          scrollwheel: false,
          zoom: 16
        });

        var marker = new google.maps.Marker({
            position: location,
            map: map,
            title: $scope.court.name
          });

      }



}]);
