<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход в систему</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #4e54c8, #8f94fb);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-container {
            background: white;
            padding: 2rem;
            border-radius: 16px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        .login-container h2 {
            margin-bottom: 1.5rem;
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 1rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #444;
        }

        .form-group input {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        .form-group input:focus {
            border-color: #4e54c8;
            outline: none;
        }

        .btn-submit {
            width: 100%;
            padding: 0.75rem;
            background-color: #4e54c8;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-weight: bold;
        }

        .btn-submit:hover {
            background-color: #3b3fc3;
        }

        .message {
            text-align: center;
            margin-top: 1rem;
            color: #e74c3c;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Вход в систему</h2>
    <form method="post" action="/login">
        <div class="form-group">
            <label for="username">Имя пользователя</label>
            <input type="text" id="username" name="username" required autofocus />
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" required />
        </div>
        <button class="btn-submit" type="submit">Войти</button>

        <#if error??>
            <div class="error">Неверный логин или пароль</div>
        </#if>
        <#if logout??>
            <div class="error" style="color:green;">Вы вышли из системы</div>
        </#if>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    </form>
</div>
</body>
</html>
