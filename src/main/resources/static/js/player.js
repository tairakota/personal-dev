document.addEventListener('keydown', function(event) {
  var goal = document.getElementById('goal');
  var player = document.getElementById('player');
  var content = document.getElementById('content');
  var rang = document.getElementById('rang');
  var playerTop = parseInt(player.style.top) || 0;
  var playerLeft = parseInt(player.style.left) || 0;
  var contentTop = parseInt(content.style.top) || 0;
  var contentLeft = parseInt(content.style.left) || 0;
  var rangTop = parseInt(rang.style.top) || 0;
  var rangLeft = parseInt(rang.style.left) || 0;

  var level = document.getElementById('levelId');

  if (level.innerText === 'hard') {

  // キー入力に応じてプレイヤーの座標を更新
  if (event.key === 'ArrowUp') {
    player.style.top = (playerTop - 30) + 'px';
    content.style.top = (contentTop - 30) + 'px';
    rang.style.top = (rangTop + 30) + 'px';
  } else if (event.key === 'ArrowDown') {
    player.style.top = (playerTop + 30) + 'px';
    content.style.top = (contentTop + 30) + 'px';
    rang.style.top = (rangTop - 30) + 'px';
  } else if (event.key === 'ArrowLeft') {
    player.style.left = (playerLeft - 30) + 'px';
    content.style.left = (contentLeft - 30) + 'px';
    rang.style.left = (rangLeft + 30) + 'px';
  } else if (event.key === 'ArrowRight') {
    player.style.left = (playerLeft + 30) + 'px';
    content.style.left = (contentLeft + 30) + 'px';
    rang.style.left = (rangLeft - 30) + 'px';
  }

  // プレイヤーと障害物の座標が重なっていた場合、プレイヤーの移動をキャンセル
  if (checkCollisionWithBlack(player)) {
    if (event.key === 'ArrowUp') {
      player.style.top = playerTop + 'px';
      content.style.top = contentTop + 'px';
      rang.style.top = rangTop + 'px';
    } else if (event.key === 'ArrowDown') {
      player.style.top = playerTop + 'px';
      content.style.top = contentTop + 'px';
      rang.style.top = rangTop + 'px';
    } else if (event.key === 'ArrowLeft') {
      player.style.left = playerLeft + 'px';
      content.style.left = contentLeft + 'px';
      rang.style.left = rangLeft + 'px';
    } else if (event.key === 'ArrowRight') {
      player.style.left = playerLeft + 'px';
      content.style.left = contentLeft + 'px';
      rang.style.left = rangLeft + 'px';
    }
  }
  } else {

    player = document.getElementById('player2');
    goal = document.getElementById('goal2');
    playerTop = parseInt(player.style.top) || 0;
    playerLeft = parseInt(player.style.left) || 0;

    // キー入力に応じてプレイヤーの座標を更新
    if (event.key === 'ArrowUp') {
      player.style.top = (playerTop - 30) + 'px';
    } else if (event.key === 'ArrowDown') {
      player.style.top = (playerTop + 30) + 'px';
    } else if (event.key === 'ArrowLeft') {
      player.style.left = (playerLeft - 30) + 'px';
    } else if (event.key === 'ArrowRight') {
      player.style.left = (playerLeft + 30) + 'px';
    }

    // プレイヤーと障害物の座標が重なっていた場合、プレイヤーの移動をキャンセル
    if (checkCollisionWithBlack(player)) {
      if (event.key === 'ArrowUp') {
        player.style.top = playerTop + 'px';
      } else if (event.key === 'ArrowDown') {
        player.style.top = playerTop + 'px';
      } else if (event.key === 'ArrowLeft') {
        player.style.left = playerLeft + 'px';
      } else if (event.key === 'ArrowRight') {
        player.style.left = playerLeft + 'px';
      }
    }
  }

  //プレイヤーとゴールが重なったらクリア
  if (checkCollision(player, goal)) {
    stopStopwatch();
  }
});

// プレイヤーと障害物の衝突をチェック
function checkCollision(player, obstacle) {
  var playerRect = player.getBoundingClientRect();
  var obstacleRect = obstacle.getBoundingClientRect();

  return (
    playerRect.top < obstacleRect.bottom &&
    playerRect.bottom > obstacleRect.top &&
    playerRect.left < obstacleRect.right &&
    playerRect.right > obstacleRect.left
  );
}

// プレイヤーとblackの衝突をチェック
function checkCollisionWithBlack(player) {
  var playerRect = player.getBoundingClientRect();
  var blocks = document.getElementsByClassName('block');

  for (var i = 0; i < blocks.length; i++) {
    var blockRect = blocks[i].getBoundingClientRect();
    if (
      playerRect.top < blockRect.bottom &&
      playerRect.bottom > blockRect.top &&
      playerRect.left < blockRect.right &&
      playerRect.right > blockRect.left &&
      blocks[i].classList.contains('black')
    ) {
      return true;
    }
  }

  return false;
}
