google.charts.load("current", { packages: ["corechart", "bar"] });
google.charts.setOnLoadCallback(CreateDistanceChart);

function CreateDistanceChart() {
  var data = new google.visualization.DataTable();
  data.addColumn("datetime", "Date");
  data.addColumn("number", "Distance");
  data.addColumn({ type: "string", role: "style" });
  data.addColumn({ type: "string", role: "annotation" });
  sportsData.forEach((sport) => {
    console.log(sport);
    data.addRow([
      new Date(sport.startTime),
      sport.distance,
      sportTypeColor(sport.activityType),
      formatSecondsToMSS(sport.averagePace),
    ]);
  });

  var options = {
    title: "Your last 30-day workout activity",
    width: 1500,
    height: 600,
    hAxis: {
      title: "Date of workout",
    },
    vAxis: {
      title: "Distance in meters",
    },
    bar: {
      width: 30,
    },
    isStacked: true,
  };

  var chart = new google.visualization.ColumnChart(
    document.getElementById("chart_div")
  );
  chart.draw(data, options);
}

function formatSecondsToMSS(seconds) {
  return (
    (seconds - (seconds %= 60)) / 60 + (9 < seconds ? ":" : ":0") + seconds
  );
}

function sportTypeColor(type) {
  switch (type) {
    case "SWIMMING":
      return "blue";
    case "OUTDOOR_CYCLING":
      return "orange";
    case "FREESTYLE":
      return "red";
    default:
      return "black";
  }
}
