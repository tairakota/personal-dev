function addEvent() {
    var name = document.getElementById("name").value;
    var pass = document.getElementById("password").value;

    document.getElementById("number").textContent = '';
    document.getElementById('error').textContent = '';

    if (name.length == 0 || pass.length == 0) {
        document.getElementById("number").textContent = '未入力の欄があります。';
        return; // 関数の実行を中断して戻る
    }

    fetch(`/addCheck?name=${name}&password=${pass}`)
    .then(res => {
        if(res.status === 400) {
            window.alert('既に同じ名前が登録されています。');
            return;
        } else {
            return res.text(); // レスポンスのテキストを返す
        }
    })
    .then(data => {
        if (data === 'error') {
          document.getElementById('error').textContent = '名前（１～１０文字）、パスワード（４～２０文字）';
        } else if(data === 'ok!') {
          document.getElementById('overlay').style.display = "block";
          document.getElementById('window').style.display = "flex";
        }
    });
}