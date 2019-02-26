app.controller("ModalLogQuarter", ['$scope','$uibModalInstance', 'quarter', 
function($scope,$uibModalInstance, quarter )
{
	$scope.quarter = quarter;
	console.log($scope.quarter);
	
	$scope.close = function(){
		$uibModalInstance.close( [] );
	};
}]);