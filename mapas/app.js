var map = document.querySelector('#map')

var paths = map.querySelectorAll('.map__image a')

let contagios = JSON.parse(dato);
for (var i = 0; i < 6; i++) {
    document.getElementById('contagio-'+String(i+1)).textContent = contagios[i];
}
let valores = JSON.parse(data);   
let departamentos = ["AMA","ANT","ARA","ATL","BOL","BOY","CAU","CES","CHO","CAL","COR","CAQ","CAS","CUN","DC","GUA","GUV","HUI","LAG","MAG","MET","NAR","NSA","PUT","QUI","RIS","SAN","SUC","SAP","TOL","VAC","VAU","VID"]

var colorear = function (v,d){
	var maxx = Math.max.apply(null, v)
	var mid = maxx/2
	for (var i = 0; i < 33; i++) {
		var r = 255 - (Math.max(v[i],mid)-mid)/mid*255
		var gb = 255 - (Math.min(v[i]/mid,1))*255
		
	    document.getElementById('region-'+d[i]).style.fill = 'rgb('+r+','+gb+','+gb+')'
	}
}

colorear(valores,departamentos);

if(NodeList.prototype.forEach === undefined) {
    NodeList.prototype.forEach = function (callback){
        [].forEach.call(this, callback)
    }
}

var activeArea = function(id){
    map.querySelectorAll('.is-active').forEach(function (item){

        item.classList.remove('is-active')

    })
    if (id !== undefined){
        document.querySelector('#region-' + id).classList.add('is-active')
    }   
}

paths.forEach(function (path) {
    path.addEventListener('mouseenter', function (e) {
       
        var id = this.id.replace('region-','')
        activeArea(id)

    })
})

    map.addEventListener('mouseover', function () {
        activeArea()
    })