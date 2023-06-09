// ページの読み込み完了時にカウントダウンを開始する
window.onload = function() {
    var level = document.getElementById('levelId');
    if(level.innerText === 'normal') {
    document.getElementById("content").style.display = "none";
    document.getElementById("obstacle2").style.display = "block";
    document.getElementById("goal2").style.display = "block";
    document.getElementById("player2").style.display = "block";
    document.getElementById("grid2").style.display = "block";
    }


    maze();
    startCountdown();
};
var level = document.getElementById('levelId');

// カウントダウンの処理
function startCountdown() {
  var countdown = 3;
  var countdownElement = document.getElementById("countdown");

  var countdownInterval = setInterval(function() {
    countdown--;
    countdownElement.innerText = countdown;

    if (countdown === 0) {
      clearInterval(countdownInterval);
      document.getElementById("countdownPopup").style.display = "none";
      document.getElementById("countdownOverlay").style.display = "none";
      startStopwatch();
    }
  }, 1000);
}

// ストップウォッチの処理
var stopwatchInterval;
var stopwatchTime = 0;

function startStopwatch() {
  var stopwatchElement = document.getElementById("stopwatch");

  stopwatchInterval = setInterval(function() {
    stopwatchTime++;
    var minutes = Math.floor(stopwatchTime / 60);
    var seconds = stopwatchTime % 60;

    var mm = document.getElementById("m");
    var ss = document.getElementById("s");
    mm.innerText = minutes;
    ss.innerText = seconds;

    stopwatchElement.innerText = minutes + ":" + formatSeconds(seconds);
  }, 1000);
}

function stopStopwatch() {
  clearInterval(stopwatchInterval);
  var stopwatchElement = document.getElementById("stopwatch");
  var clearTimeSet = document.getElementById("clearTime");
  clearTimeSet.innerText = stopwatchElement.innerText;
  document.getElementById("clearOverlayId").style.display = "flex";
  document.getElementById("clearPopupId").style.display = "flex";

  const m2 = document.getElementById("m");
  const s2 = document.getElementById("s");
  var m = m2.innerText;
  var s = s2.innerText;
  var stopWatch = stopwatchElement.innerText;

  if (level.innerText === 'normal') {
  fetch(`/playTest-score?m=${m}&s=${s}&stopWatch=${stopWatch}`)
  } else {
  fetch(`/playTest-scoreH?m=${m}&s=${s}&stopWatch=${stopWatch}`)
  }
}

// 秒を2桁表示にフォーマットする
function formatSeconds(seconds) {
  return seconds < 10 ? "0" + seconds : seconds;
}