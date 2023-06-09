window.onload = function() {

  // 二次元配列のデータ
  var array2D = [
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1],
      [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1]
  ];

  var grid = document.getElementById("grid");

  // 二次元配列の要素を走査してdiv要素を作成し、表示する
  for (var i = 0; i < array2D.length; i++) {
      for (var j = 0; j < array2D[i].length; j++) {
          var block = document.createElement("div");
          block.className = "block";
          block.classList.add(array2D[i][j] === 0 ? "white" : "black");
          grid.appendChild(block);
      }
      var lineBreak = document.createElement("br");
      grid.appendChild(lineBreak);
  }

};
