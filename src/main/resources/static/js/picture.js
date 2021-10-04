google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var slices = [];
  var data = new google.visualization.DataTable();
  data.addColumn("string", "color");
  data.addColumn("number", "number of pixels");
  Object.entries(colorData).forEach(([key, value]) => {
    console.log(key + "," + value);
    data.addRow([key, value]);
    slices.push({
        color: key
    });
  });

  console.log(slices);

  var options = {
    pieHole: 0.5,
    pieSliceTextStyle: {
      color: "black",
    },
    legend: "none",
    slices : slices,
  };

  var chart = new google.visualization.PieChart(
    document.getElementById("donut_single")
  );
  chart.draw(data, options);
}
