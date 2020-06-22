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

var casos_hoy = [];
casos_hoy[0] = JSON.parse(casa);
casos_hoy[1] = JSON.parse(hospital);
casos_hoy[2] = JSON.parse(uci);
casos_hoy[3] = JSON.parse(recuperados);
casos_hoy[4] = JSON.parse(fallecidos);

var hombre = JSON.parse(hombres);
var mujer = JSON.parse(mujeres);

var edad = [
  [
  JSON.parse(cero_confirmados),
  JSON.parse(diez_confirmados),
  JSON.parse(veinte_confirmados),
  JSON.parse(treinta_confirmados),
  JSON.parse(cuarenta_confirmados),
  JSON.parse(cincuenta_confirmados),
  JSON.parse(sesenta_confirmados),
  JSON.parse(setenta_confirmados),
  JSON.parse(ochenta_confirmados),
  JSON.parse(noventa_confirmados)
  ] , 
  [
  JSON.parse(cero_recuperados),
  JSON.parse(diez_recuperados),
  JSON.parse(veinte_recuperados),
  JSON.parse(treinta_recuperados),
  JSON.parse(cuarenta_recuperados),
  JSON.parse(cincuenta_recuperados),
  JSON.parse(sesenta_recuperados),
  JSON.parse(setenta_recuperados),
  JSON.parse(ochenta_recuperados),
  JSON.parse(noventa_recuperados)
  ] ,
  [
  JSON.parse(cero_fallecidos),
  JSON.parse(diez_fallecidos),
  JSON.parse(veinte_fallecidos),
  JSON.parse(treinta_fallecidos),
  JSON.parse(cuarenta_fallecidos),
  JSON.parse(cincuenta_fallecidos),
  JSON.parse(sesenta_fallecidos),
  JSON.parse(setenta_fallecidos),
  JSON.parse(ochenta_fallecidos),
  JSON.parse(noventa_fallecidos)
  ]];

var depart = 20;
var tipo_histograma = 0;

var data_genero = [
    {label: "Hombres", value: hombre[20]},
    {label: "Mujeres", value: mujer[20]}
  ]

var data_casos = [
    {label: "Casa", value: casos_hoy[0][20]},
    {label: "Hospital", value: casos_hoy[1][20]},
    {label: "UCI", value: casos_hoy[2][20]},
    {label: "Recuperados", value: casos_hoy[3][20]},
    {label: "Fallecidos", value: casos_hoy[4][20]}
  ]

var morris_genero = Morris.Donut({
  element: 'genero',
  data: data_genero,
  colors: ['#205BFF', '#2093FF'],
  labelColor:"#FFFFFF",
  resize: true
});

var morris_casos = Morris.Donut({
  element: 'casos',
  data: data_casos,
  colors: ['#34495E','#58D68D','#F84147','#09A2D7', '#9EA2A2'],
  labelColor:"#FFFFFF",
  resize: true
});

var data_histograma = [
  { y: '0', a: edad[0][0][20]},
  { y: '10', a: edad[0][1][20]},
  { y: '20', a: edad[0][2][20]},
  { y: '30', a: edad[0][3][20]},
  { y: '40', a: edad[0][4][20]},
  { y: '50', a: edad[0][5][20]},
  { y: '60', a: edad[0][6][20]},
  { y: '70', a: edad[0][7][20]},
  { y: '80', a: edad[0][8][20]},
  { y: '90+', a: edad[0][9][20]}
],
config = {
  data: data_histograma,
  xkey: ['y'],
  ykeys: ['a'],
  labels: ['Casos Confirmados'],
  resize: true
};

config.element = 'histograma';
var morris_histograma = Morris.Bar(config);

function actualizar_tipo_histogramaBogota(tipo){
  tipo_histograma = tipo;
  morris_histograma.setData([
      { y: '0', a: edad[tipo][0][depart]},
      { y: '10', a: edad[tipo][1][depart]},
      { y: '20', a: edad[tipo][2][depart]},
      { y: '30', a: edad[tipo_histograma][3][depart]},
      { y: '40', a: edad[tipo_histograma][4][depart]},
      { y: '50', a: edad[tipo_histograma][5][depart]},
      { y: '60', a: edad[tipo_histograma][6][depart]},
      { y: '70', a: edad[tipo_histograma][7][depart]},
      { y: '80', a: edad[tipo_histograma][8][depart]},
      { y: '90+', a: edad[tipo_histograma][9][depart]}
    ]);
}

function actualizar_graficas_hoyBogota(localidad){
  depart = localidad;
  morris_genero.setData([
      {label: "Hombres", value: hombre[localidad]},
      {label: "Mujeres", value: mujer[localidad]}
   ]);
  
  morris_casos.setData([
    {label: "Casa", value: casos_hoy[0][localidad]},
    {label: "Hospital", value: casos_hoy[1][localidad]},
    {label: "UCI", value: casos_hoy[2][localidad]},
    {label: "Recuperados", value: casos_hoy[3][localidad]},
    {label: "Fallecidos", value: casos_hoy[4][localidad]}
  ]);
  
  morris_histograma.setData([
      { y: '0', a: edad[tipo_histograma][0][localidad]},
      { y: '10', a: edad[tipo_histograma][1][localidad]},
      { y: '20', a: edad[tipo_histograma][2][localidad]},
      { y: '30', a: edad[tipo_histograma][3][localidad]},
      { y: '40', a: edad[tipo_histograma][4][localidad]},
      { y: '50', a: edad[tipo_histograma][5][localidad]},
      { y: '60', a: edad[tipo_histograma][6][localidad]},
      { y: '70', a: edad[tipo_histograma][7][localidad]},
      { y: '80', a: edad[tipo_histograma][8][localidad]},
      { y: '90+', a: edad[tipo_histograma][9][localidad]}
    ]);
}

actualizar_graficas_hoyBogota(0);
colorear(0);