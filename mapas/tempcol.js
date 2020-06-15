var casos = [4];
casos[0] = [33][80];
casos[1] = [33][80];
casos[2] = [33][80];
casos[3] = [33][80];

casos[0] = JSON.parse(total);
casos[1] = JSON.parse(activ);
casos[2] = JSON.parse(recup);
casos[3] = JSON.parse(falle);

Date.prototype.subDays = function(days) {
  var dat = new Date(this.valueOf())
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

var dateArray = getDates((new Date()).subDays(79),new Date());
for (i = 0; i < dateArray.length; i ++ ) {
    console.log(dateArray[i]);
}

function myFunction() {
  G.setData(updateGrafica());
  var e = document.getElementById("deps");
  var titulo = e.options[e.selectedIndex].text;
  document.getElementById("ubic").innerHTML = titulo;
}

function updateGrafica(){
  var z=0;
  var morrisData = [];
  
  for (tot=dateArray.length; z < tot; z++) {
    morrisData.push({'day': dateArray[z], 'total': casos[0][document.getElementById("deps").selectedIndex][z], 'activ': casos[1][document.getElementById("deps").selectedIndex][z], 'recup': casos[2][document.getElementById("deps").selectedIndex][z], 'falle': casos[3][document.getElementById("deps").selectedIndex][z]});
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
    ykeys: ['total', 'activ','recup','falle'],
    // Labels for the ykeys -- will be displayed when you hover over the
    // chart.
    labels: ['confirmados','activos','recuperados', 'fallecidos'],
    resize: true,
    lineColors: ['red','yellow','blue','grey'],
    pointStrokeColors: ['red','yellow','blue','grey'],
  });


