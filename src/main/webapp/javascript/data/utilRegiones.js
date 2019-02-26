
function obtenerComunas(region_id){
	var comunas = [];
	var provincias = provinciaRegion[region_id];
	for( i in provincias ){
		var listaComunas = comunaProvincia[provincias[i].provincia_id];
		comunas = comunas.concat(listaComunas);
	}
	return comunas
}