/*************************************************************
 * @author Juan Francisco ( juan.maldonado.leon@gmail.com )
 * @desc Servicio Generico para invocacion de Servicios Rest
 *************************************************************/
app.service('GenericService', ['$http', '$q',function($http, $q){

	var path = CONSTANTS.contextPath + "/";


	/**
	 *
	 */
	this.getAll = function( nameObjects ){
		var defered = $q.defer();
        var promise = defered.promise;
		var request = $http.get( path + nameObjects );
		request.success( function( response )
		{
			 defered.resolve(response);
		});
		request.error( function( error )
		{
			 defered.reject(error)
		});
		return promise;
	};


	/**
	 *
	 */
	this.save = function( nameObjects, object ){
		var defered = $q.defer();
        var promise = defered.promise;
		var request = $http.put( path + nameObjects, object );
		request.success( function( response )
		{
			 defered.resolve(response);
		});
		request.error( function( error )
		{
			 defered.reject(error)
		});
		return promise;
	};




	/**
	 *
	 */
	this.getById = function( nameObjects, oid ){
		var defered = $q.defer();
        var promise = defered.promise;
		var request = $http.get( path + nameObjects + "/" + oid );
		request.success( function( response )
		{
			 defered.resolve(response);
		});
		request.error( function( error )
		{
			 defered.reject(error)
		});
		return promise;
	};

}]);
