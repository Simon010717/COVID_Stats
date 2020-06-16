var casos = [4];
casos[0] = [33][80];
casos[1] = [33][80];
casos[2] = [33][80];
casos[3] = [33][80];

casos[0] = JSON.parse(total);
casos[1] = JSON.parse(activ);
casos[2] = JSON.parse(recup);
casos[3] = JSON.parse(falle);

var graficas = [3];
  graficas[0] = [];
  graficas[1] = [];
  graficas[2] = [];

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

function tipo_grafica(c){
  var z=0;
  switch (c) {
    case "0":
      console.log("case 0");
      graficas[0] = [];
      for (tot=dateArray.length; z < tot; z++) {
        graficas[0].push({'day': dateArray[z], 'confirmados': casos[0][document.getElementById("deps").selectedIndex][z], 'activos': casos[1][document.getElementById("deps").selectedIndex][z], 'recuperados': casos[2][document.getElementById("deps").selectedIndex][z], 'fallecidos': casos[3][document.getElementById("deps").selectedIndex][z]});
      }
      graficas[1] = ['confirmados','activos','recuperados', 'fallecidos'];
      graficas[2] = ['red','yellow','blue','grey'];
      break;

    case "1":
      console.log("case 1");
      graficas[0] = [];
      for (tot=dateArray.length; z < tot; z++) {
        graficas[0].push({'day': dateArray[z], 'activos': casos[1][document.getElementById("deps").selectedIndex][z]});
      }
      graficas[1] = ['activos'];
      graficas[2] = ['yellow'];

      
      break;

    case "2":
      console.log("case 2");
      graficas[0] = [];
      for (tot=dateArray.length; z < tot; z++) {
        graficas[0].push({'day': dateArray[z], 'recuperados': casos[2][document.getElementById("deps").selectedIndex][z]});
      }
      graficas[1] = ['recuperados'];
      graficas[2] = ['blue'];

      
      break;

    case "3":
      console.log("case 3"); 
      graficas[0] = [];
      for (tot=dateArray.length; z < tot; z++) {
        graficas[0].push({'day': dateArray[z], 'fallecidos': casos[3][document.getElementById("deps").selectedIndex][z]});
      }
      graficas[1] = ['fallecidos'];
      graficas[2] = ['grey'];
      break;

    default: 
      break;
  }
  G.setData(graficas[0]);
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
    morrisData.push({'day': dateArray[z], 'confirmados': casos[0][document.getElementById("deps").selectedIndex][z], 'activos': casos[1][document.getElementById("deps").selectedIndex][z], 'recuperados': casos[2][document.getElementById("deps").selectedIndex][z], 'fallecidos': casos[3][document.getElementById("deps").selectedIndex][z]});
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
    ykeys: ['confirmados','activos','recuperados', 'fallecidos'],
    // Labels for the ykeys -- will be displayed when you hover over the
    // chart.
    labels: ['confirmados','activos','recuperados', 'fallecidos'],
    resize: true,
    lineColors: ['red','yellow','blue','grey'],
    pointStrokeColors: ['red','yellow','blue','grey'],
  });

