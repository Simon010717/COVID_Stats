Date.prototype.addDays = function(days) {
  var dat = new Date(this.valueOf())
  dat.setDate(dat.getDate() + days);
  return dat;
}

function getDates(startDate, stopDate) {
 var dateArray = new Array();
 var currentDate = startDate;
 while (currentDate <= stopDate) {
   dateArray.push(currentDate)
   currentDate = currentDate.addDays(1);
 }
 return dateArray;
}
new Morris.Line({
    // ID of the element in which to draw the chart.
    element: 'myfirstchart',
    // Chart data records -- each entry in this array corresponds to a point on
    // the chart.
    data: [
      { year: '2008', value: 20, value2: 30, value3: 10, value4: 20 },
      { year: '2009', value: 10, value2: 40, value3: 35, value4: 45 },
      { year: '2010', value: 5 , value2: 10, value3: 10, value4: 20 },
      { year: '2011', value: 5 , value2: 5, value3: 50, value4: 60 },
      { year: '2012', value: 105 , value2: 5, value3: 50, value4: 60 },
      { year: '2013', value: 5 , value2: 5, value3: 50, value4: 60 },
      { year: '2014', value: 20, value2: 40, value3: 40, value4: 50 }
    ],
    
    // The name of the data record attribute that contains x-values.
    xkey: 'year',
    // A list of names of data record attributes that contain y-values.
    ykeys: ['value', 'value2','value3','value4'],
    // Labels for the ykeys -- will be displayed when you hover over the
    // chart.
    labels: ['confirmados','activos','recuperados', 'fallecidos'],
    resize: true,
    lineColors: ['red','yellow','blue','grey'],
  });

  var casos = [4];
casos[0] = [37][81];
casos[1] = [37][81];
casos[2] = [37][81];
casos[3] = [37][81];

casos[0] = JSON.parse(total);
casos[1] = JSON.parse(activ);
casos[2] = JSON.parse(recup);
casos[3] = JSON.parse(falle);

console.log(casos[0][3][80] + casos[1][27][80]);

