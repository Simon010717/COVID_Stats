var map = document.querySelector('#mapa')

var paths = map.querySelectorAll('.map__image a')

var casos = [];
casos[0] = JSON.parse(conf);
casos[1] = JSON.parse(act);
casos[2] = JSON.parse(rec);
casos[3] = JSON.parse(fall);

let contagios = JSON.parse(crec);
for (var i = 0; i < 6; i++) {
    document.getElementById('contagio-'+String(i+1)).textContent = contagios[i];
}  
var deps = ["ATN","BRU","BOS","CHP","CBL","ENG","FON","KEN","LCD","LMA","PAR","RUB","SCB","STF","SUB","SUM","TEU","TUN","USQ","USM"]

var colorear = function (c){
    v = casos[c]
    var maxx = Math.max.apply(null, v)
    var mid = maxx/2
    var r = 255
    var g = 255
    var b = 255
    //document.getElementById('region-AMA'.xlink = "hola"
    //document.getElementById('region-AMA1').style.fill = 'rgb(0,0,0)'
    if(c===0){ //confirmados
        for (var i = 0; i < 33; i++) {
            r = 255 - (Math.max(v[i],mid)-mid)/mid*255
            g = 255 - (Math.min(v[i]/mid,1))*255
            b = g
            document.getElementById('a-region-'+deps[i]).style.fill = 'rgb('+r+','+g+','+b+')'
            document.getElementById('a-region-'+deps[i]).setAttribute("xlink:title",document.getElementById('region-'+deps[i]).getAttribute("title")+":"+String(v[i]))
        }
    } else if (c===1){ //activos
        for (var i = 0; i < 33; i++) {
            r = 255
            g = 255 - Math.max(0,(v[i]/maxx)*300-255)
            b = 255 - Math.min(255,(v[i]/maxx)*300)
            document.getElementById('a-region-'+deps[i]).style.fill = 'rgb('+r+','+g+','+b+')'
            document.getElementById('a-region-'+deps[i]).setAttribute("xlink:title",document.getElementById('region-'+deps[i]).getAttribute("title")+":"+String(v[i]))
        }
    } else if (c===2){ //recuperados
        for (var i = 0; i < 33; i++) {
            r = 255 - (v[i]/maxx)*255
            g = 255 - (v[i]/maxx)*85
            b = 255
            document.getElementById('a-region-'+deps[i]).style.fill = 'rgb('+r+','+g+','+b+')'
            document.getElementById('a-region-'+deps[i]).setAttribute("xlink:title",document.getElementById('region-'+deps[i]).getAttribute("title")+":"+String(v[i]))
        }
    }  else if (c===3){ //recuperados
        for (var i = 0; i < 33; i++) {
            r = 255 - (v[i]/maxx)*200
            g = 255 - (v[i]/maxx)*200
            b = 255 - (v[i]/maxx)*200
            document.getElementById('a-region-'+deps[i]).style.fill = 'rgb('+r+','+g+','+b+')'
            document.getElementById('a-region-'+deps[i]).setAttribute("xlink:title",document.getElementById('region-'+deps[i]).getAttribute("title")+":"+String(v[i]))
        }
    }    
}

var tipo_mapa = function(c){
    colorear(c)
    var butts = ['conf','act','rec','fall']
    document.getElementById(butts[c]+'_button').setEnabled = false;
}

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
        document.querySelector('#a-region-' + id).classList.add('is-active')
    }   
}

paths.forEach(function (path) {
    path.addEventListener('mouseenter', function (e) {
       
        var id = this.id.replace('a-region-','')
        activeArea(id)

    })
})

map.addEventListener('mouseover', function () {
    activeArea()
})