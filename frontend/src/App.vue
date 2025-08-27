<template>
  <div>
    <h1>📊 Realtime Dashboard</h1>
    <button @click="sendRandomData">Kirim Data</button>
    <canvas id="myChart"></canvas>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { connect, sendMessage } from "./websocket";
import Chart from "chart.js/auto";

let chart;

onMounted(() => {
  const ctx = document.getElementById("myChart");
  chart = new Chart(ctx, {
    type: "line",
    data: { labels: [], datasets: [{ label: "Realtime Data", data: [] }] }
  });

  connect((msg) => {
    const value = parseInt(msg);
    chart.data.labels.push(new Date().toLocaleTimeString());
    chart.data.datasets[0].data.push(value);
    chart.update();
  });
});

function sendRandomData() {
  const value = Math.floor(Math.random() * 100);
  sendMessage(value.toString());
}
</script>