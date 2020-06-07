var map = document.querySelector('#map')

var paths = map.querySelectorAll('.map__image a')

let contagios = JSON.parse(dato);
for (var i = 0; i < 6; i++) {
    document.getElementById('contagio-'+String(i+1)).textContent = contagios[i];
}

let valores = JSON.parse(data);   
//let valores = [0,11,2095,186,5,58,270,0,241,1650,3,649,0,967,216,48,233,0,0,432]
let localidades = ["ATN","BRU","BOS","CHP","CBL","ENG","FON","KEN","LCD","LMA","PAR","RUB","SCB","STF","SUB","SUM","TEU","TUN","USQ","USM"]

var colorear = function (v,d){
	var maxx = Math.max.apply(null, v)
	var mid = maxx/2
	for (var i = 0; i < 20; i++) {
		var r = 255 - (Math.max(v[i],mid)-mid)/mid*255
		var gb = 255 - (Math.min(v[i]/mid,1))*255
		
	    document.getElementById('region-'+d[i]).style.fill = 'rgb('+r+','+gb+','+gb+')'
	}
}

colorear(valores,localidades);

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

        document.querySelector('#region-' + id).classList.add('is-active');
    }
}

paths.forEach(function (path) {
    path.addEventListener('mouseenter', function (e) { // para que se activee la región
        var id = this.id.replace('region-','')
        activeArea(id)
    })
})
    map.addEventListener('mouseover', function () { //Para que no se quede activada la region
        activeArea()
    })