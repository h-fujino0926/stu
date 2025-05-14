<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
  <title>得点管理システム</title>
  <style>
    body {
      font-family: sans-serif;
      background-color: #f6f6f6;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }

    .container {
      background-color: #fff;
      border-radius: 8px;
      padding: 30px;
      width: 400px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    h2 {
      background-color: #e4efff;
      padding: 10px;
      margin: -30px -30px 30px -30px;
      font-size: 24px;
    }

    .form-group {
      margin-bottom: 20px;
      text-align: left;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="password"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .checkbox-group {
      text-align: left;
      margin-bottom: 20px;
    }

    .checkbox-group input[type="checkbox"] {
      margin-right: 5px;
    }

    button {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 10px 20px;
      font-size: 16px;
      border-radius: 5px;
      cursor: pointer;
    }

    footer {
      margin-top: 20px;
      font-size: 12px;
      color: #888;
    }
  </style>
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
    </div>

  <script>
    function togglePassword() {
      const passwordField = document.getElementById("password");
      passwordField.type = passwordField.type === "password" ? "text" : "password";
    }
  </script>
</body>
</html>
