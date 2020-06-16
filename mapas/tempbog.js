var casos = [];
casos = [20][80];

casos = JSON.parse(total);

var fechaActual = JSON.stringify(fecha);
fechaActual.toString();
new Date(fechaActual);
console.log(fechaActual);

Date.prototype.subDays = function(days) {
  var dat = new Date(fechaActual);
  dat.setDate(dat.getDate() - days);
  return dat;
}

function getDates(startDate, stopDate) {
  var dateArray = [];
  var currentDate = moment(startDate);
  var stopDate = moment(stopDate);
  while (currentDate <= stopDate) {
      dateArray.push( moment(currentDate).format('YYYY-MM-DD') )
      currentDate = moment(currentDate).add(1, 'days');
  }
  return dateArray;
}

var dateArray = getDates(new Date(fechaActual).subDays(79),new Date(fechaActual));


function myFunction() {
  G.setData(updateGrafica());
  var e = document.getElementById("locs");
  var titulo = e.options[e.selectedIndex].text;
  document.getElementById("ubic").innerHTML = titulo;
}


function updateGrafica(){
  var z=0;
  var morrisData = [];
  
  for (tot=dateArray.length; z < tot; z++) {
    morrisData.push({'day': dateArray[z], 'total': casos[document.getElementById("locs").selectedIndex][z]});
  }
  return morrisData;
}


var G = Morris.Line({
    // ID of the element in which to draw the chart.
    element: 'myfirstchart',
    // Chart data records -- each entry in this array corresponds to a point on
    // the chart.

    data: updateGrafica(),   
    
    // The name of the data record attribute that contains x-values.
    xkey: 'day',
    // A list of names of data record attributes that contain y-values.
    ykeys: ['total'],
    // Labels for the ykeys -- will be displayed when you hover over the
    // chart.
    labels: ['confirmados'],
    resize: true,
    lineColors: ['red'],
    pointStrokeColors: ['red'],
  });


