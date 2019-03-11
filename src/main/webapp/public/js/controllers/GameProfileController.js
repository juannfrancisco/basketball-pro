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

			$scope.match.result = $scope.match.scoreLocal > $scope.match.scoreVisitor;

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


    $scope.statsPlayerLocal = [];
    $scope.statsPlayerVisitor = [];

    $scope.scoreQuarterLocal = {};
    $scope.scoreQuarterVisitor = {};

    $scope.calculateStats = function(){

        var pointsLocal = 0;
        var pointsVisitor = 0;
        for (var i in $scope.stats){
            if( $scope.stats[i].typeTeam == 'LOCAL' &&  $scope.stats[i].type == "PTS"){

               $scope.addStatPlayer( $scope.statsPlayerLocal , $scope.stats[i].player, $scope.stats[i].type, $scope.stats[i].value );

               pointsLocal = pointsLocal + $scope.stats[i].value;
            }else{
                $scope.addStatPlayer( $scope.statsPlayerVisitor , $scope.stats[i].player, $scope.stats[i].type, $scope.stats[i].value );
                pointsVisitor = pointsVisitor + $scope.stats[i].value;
            }
        }
        $scope.match.scoreLocal = pointsLocal;
        $scope.match.scoreVisitor = pointsVisitor;
        $scope.groupByQuarter();
    }




    $scope.groupByQuarter = function(){
        for (var i in $scope.stats){
            if( $scope.stats[i].typeTeam == 'LOCAL' && $scope.stats[i].type == "PTS" ){
                var val = $scope.scoreQuarterLocal[$scope.stats[i].quarter] ?  $scope.scoreQuarterLocal[$scope.stats[i].quarter]:0;
                $scope.scoreQuarterLocal[$scope.stats[i].quarter] = val + $scope.stats[i].value;
            }else{
                var val = $scope.scoreQuarterVisitor[$scope.stats[i].quarter] ?  $scope.scoreQuarterVisitor[$scope.stats[i].quarter]:0;
                $scope.scoreQuarterVisitor[$scope.stats[i].quarter] = val + $scope.stats[i].value;
            }
        }

    }



    $scope.addStatPlayer = function( team, player, typeStat, value ){


        if( team.length > 0 ){
            for( i in team ){
                if( team[i].oid == player.oid ){
                    team[i][typeStat] = team[i][typeStat] ? team[i][typeStat] + value : value;
                    return;
               }
            }

            player[typeStat] = value;
            team.push( player );
        }
        else{
            player[typeStat] = value;
            team.push( player );
        }
    }

	$scope.loadData();

}]);
