/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Controlador MatchPlayingController
 *************************************************************/
app.controller("MatchPlayingController", ['$scope', '$http', '$routeParams', '$interval', '$uibModal', '$window',
function($scope, $http, $routeParams, $interval,$uibModal, $window)
{
	$scope.flagLoading = true;
	$scope.timeInit = 20;
	$scope.time = 20;
	$scope.timetext = "00:00:00";
	$scope.score = { visitor:0, local:0 };
	$scope.scoreText = { visitor:{d1:0, d2:0, d3:0}, local:{ d1:0, d2:0, d3:0 } };
	$scope.point = {team:'', score:0};
	$scope.quarter = [];

	var MATCH = "match-";



	/**
	 *
	 */
	$scope.loadData = function()
	{
		$scope.flagLoading = true;
		NProgress.start();

		var request =
		$http.get( CONSTANTS.contextPath + "/matches/" + $routeParams.id );
		request.success( function( response )
		{
			$scope.match = response;
			$scope.match.date = new Date( $scope.match.date );
			$scope.match.visitor.formacion = false;
			$scope.match.local.formacion = false;
			$scope.loadPlayers( $scope.match.local );
			$scope.loadPlayers( $scope.match.visitor );
			$scope.flagLoading = false;

			$scope.statsLocal = $window.localStorage ? JSON.parse( $window.localStorage.getItem( MATCH+ $scope.match.oid ) ) : null;

			NProgress.done();
		} );
		request.error( function( error )
		{
			$scope.flagLoading = false;
			NProgress.done();
		});
	};
	$scope.loadData();

	$scope.deleteDataLocal = function(){
	    if( $window.localStorage && $window.localStorage.getItem( MATCH+ $scope.match.oid )  ){
	        $window.localStorage.removeItem(MATCH+ $scope.match.oid);
	    }
	}


	$scope.loadPlayers = function(team){

	    var request = $http.get( CONSTANTS.contextPath + "/teams/n/"+team.nameURL+"/players");
        request.success( function( response )
        {
            team.players = response;
        } );
        request.error( function( error )
        {
            console.log( error );
        });

	};



	$scope.calculateDigit = function( score , scoreText ){
		var digits = [];
		var i = 0;
		while( i < 3  ){
			var digit = score % 10;
			score = Math.floor(score/10);
			digits.push(digit);
			i++;
		}
		scoreText.d3 = digits[0];
		scoreText.d2 = digits[1];
		scoreText.d1 = digits[2];

		return digits;
	};

	var stop;
	$scope.stateWatch = "stop"
	
	/**
	 * 
	 */
	$scope.startCount = function(){
	    debugger;

	    if( $scope.stateWatch == "stop" ){
	        $scope.quarter.push( {name: ($scope.quarter.length + 1 ) + "quarter",points:[]} );
	    }

		if ( angular.isDefined(stop) ) return;
		$scope.stateWatch = "play"
		stop = $interval(function() {
			if ($scope.time > 0 ) {
				$scope.time = $scope.time -1;
				var hour =Math.floor( $scope.time / 3600 );
				var min = Math.floor( $scope.time / 60 );
				var seconds = $scope.time % 60;
				$scope.timetext = (hour <10 ? "0"+hour:hour) + ":" + (min <10 ? "0"+ min: min) + ":"+ (seconds<10 ? "0"+seconds: seconds);
			} else {
				$scope.stopQuarter();
			}
        }, 1000);
	};

	/**
	 * 
	 */
	$scope.pauseCount = function(){
	    debugger;
		if (angular.isDefined(stop)) {
            $interval.cancel(stop);
            stop = undefined;
            $scope.stateWatch = "pause";
          }
	};
	
	/**
	 * 
	 */
	$scope.stopCount = function(){
		debugger;
		//alert( "Desea terminar el partido ? " );
		if (angular.isDefined(stop)) {
            $interval.cancel(stop);
            stop = undefined;
        }
		$scope.time = $scope.timeInit;
        $scope.timetext = "00:00:00";
        $scope.stateWatch = "stop";
	};



	$scope.stopQuarter = function(){
        if (angular.isDefined(stop)) {
            $interval.cancel(stop);
            stop = undefined;
        }
        $scope.time = $scope.timeInit;
        $scope.timetext = "00:00:00";
        $scope.stateWatch = "stop";
    };
	
	/**
	 * 
	 */
	$scope.addCrew = function(type){
		var modalInstance = $uibModal.open({
            animation: true,
            backdrop : false,
            templateUrl: 'pages/matches/modal-add-crew.html',
            controller: 'ModalAddCrew',
            size :'lg',
            resolve: {
                match: function () {
                  return $scope.match;
                }
              }
        });
		
		modalInstance.result.then(function (selectedItem) {
			$scope.match[type].formacion = true;
	    }, function () {
	      console.log("xxssss");
	    });
	};

	
	/**
	 * 
	 */
	$scope.optionPlayer = function( player, type ){
		var modalInstance = $uibModal.open({
            animation: true,
            backdrop : false,
            templateUrl: 'pages/matches/modal-option-player.html',
            controller: 'ModalOptionPlayer',
            size :'lg',
            resolve: {
            	player: function () {
                  return player;
                },
                score : function(){
                	return $scope.score[type];
                }
              }
        });
		
		modalInstance.result.then(function (stat) {

            $scope.addMatchStatLocal(stat);
		    //$scope.addMatchStat(stat);

            if(stat.type == "PTS"){
                stat.quarter = $scope.quarter.length;
                stat.match = {oid: $scope.match.oid};
                stat.player = {oid: stat.player.oid };

                var scoreNew = stat.value
                $scope.score[type] = $scope.score[type]+ scoreNew;
                $scope.calculateDigit($scope.score[type], $scope.scoreText[type] );

                player.totalPoints = player.totalPoints + scoreNew;
                player.points = player.points ? player.points : [];
                player.points.push( { date: new Date(), point:scoreNew, quarter:$scope.quarter.length-1 } );
                //  $scope.quarter[$scope.quarter.length-1].points.push( { date: new Date(), point:scoreNew, player:player } );
            }

			
	    }, function () {
	      console.log("xxssss");
	    });
	};





    $scope.addMatchStatLocal = function(stat){

        stat.player = {oid:stat.player.oid};
        if( $window.localStorage ){
            if($window.localStorage.getItem( MATCH+ $scope.match.oid ) ){
                var matchStatLocalStr = $window.localStorage.getItem( MATCH+ $scope.match.oid );
                var matchStatLocal = JSON.parse(matchStatLocalStr);
                matchStatLocal.stats.push( stat );

                $window.localStorage.setItem(MATCH+$scope.match.oid, JSON.stringify(matchStatLocal));
            }else{
                var matchStatLocal = {stats : [stat]};

                $window.localStorage.setItem(MATCH+$scope.match.oid, JSON.stringify(matchStatLocal));
            }
        }else{
            alert("No se guardaran los datos en el dispositivo");
        }
	}

	$scope.addMatchStat = function(stat){
        var request = $http.put( CONSTANTS.contextPath + "/matchstats/", stat);
        request.success( function( response )
        {
            console.log("sa");
        } );
        request.error( function( error )
        {
            console.log(error);
        });

	}
	/**
	 * 
	 */
	$scope.logQuarter = function(type){
		var modalInstance = $uibModal.open({
            animation: true,
            backdrop : false,
            templateUrl: 'pages/matches/modal-log-quarter.html',
            controller: 'ModalLogQuarter',
            size :'lg',
            resolve: {
                quarter: function () {
                  return $scope.quarter[$scope.quarter.length-1];
                }
              }
        });
		modalInstance.result.then(function (selectedItem) {
			
	    }, function () {
	      
	    });
	};
	
}]);




/**
 * 
 */
app.controller("ModalAddCrew", ['$scope','$uibModalInstance', 'match',
function($scope,$uibModalInstance, match )
{
	$scope.match = match;
	$scope.selectedTeam = false;
	$scope.listPlayers = [];
	
	/**
	 * 
	 */
	$scope.selectTeam  = function(team){
		$scope.selectedTeam = true;
		$scope.team = team;
		$scope.players = angular.copy(team.players);
		$scope.normalizeData( $scope.players );
	}
	
	
	/**
	 * 
	 */
	$scope.normalizeData = function(players){
		for( i in players){
			if( players[i].titular ){
				$scope.listPlayers[players[i].oid] = players[i].oid;
			}
		}
		$scope.totalSelected = Object.keys($scope.listPlayers).length
	}
	
	/**
	 * 
	 */
	$scope.cancel = function()
	{
		$uibModalInstance.close( [] );
	}
	
	/**
	 * 
	 */
	$scope.addCrew = function(){
		
		for( i in $scope.team.players ){
			$scope.team.players[i].titular = false;
			$scope.team.players[i].titular = $scope.listPlayers[$scope.team.players[i].oid]; // && $scope.listPlayers[$scope.team.players[i].oid].titular;
		}
		$uibModalInstance.close( $scope.listPlayers );
	}
	
	
	/**
	 * 
	 */
	$scope.selectPlayer = function(player){

		$scope.q = "";
		$scope.totalSelected = Object.keys($scope.listPlayers).length;
		
		if( !player.titular ){
			if( $scope.totalSelected < 5 ){
				$scope.listPlayers[player.oid] = player;
				player.titular = !player.titular;
			}
		}else
		{
			delete $scope.listPlayers[player.oid]
			player.titular = !player.titular;
		}
		$scope.totalSelected = Object.keys($scope.listPlayers).length;
	}
 }]);



app.controller("ModalOptionPlayer", ['$scope','$uibModalInstance', 'player', 'score',
function($scope,$uibModalInstance, player, score )
{
	$scope.player = player;
	console.log(player);
	
	/**
	 * 
	 */
	$scope.addStat = function(value,type){
		$uibModalInstance.close( {value:value, type:type, player: player} );
	}
	
	/**
	 * 
	 */
	$scope.close = function()
	{
		$uibModalInstance.close( 0 );
	}
}]);
