<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>得点管理システム</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div class="container">
    <h2>得点管理システム</h2>
    <form>
      <div class="form-group">
        <label for="id">ID</label>
        <input type="text" id="id" name="id" value="admin">
      </div>
      <div class="form-group">
        <label for="password">パスワード</label>
        <input type="password" id="password" name="password" value="Passwd06112023!">
      </div>
      <div class="checkbox-group">
        <input type="checkbox" id="chk_d_ps" name="chk_d_ps" onclick="togglePassword()">
        <label for="chk_d_ps">パスワードを表示</label>
      </div>
      <button type="submit" name="login">ログイン</button>
    </form>
    <footer>
      © 2023 TIC<br>大原学園
    </footer>
  </div>

  <script>
    function togglePassword() {
      const passwordField = document.getElementById("password");
      passwordField.type = passwordField.type === "password" ? "text" : "password";
    }
  </script>
</body>
</html>
