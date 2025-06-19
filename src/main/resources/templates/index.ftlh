<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Система принятия решений</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: white;
            padding: 1rem;
            text-align: center;
        }
        main {
            display: flex;
            gap: 20px;
            padding: 20px;
        }
        section {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            flex: 1;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #f0f0f0;
        }
        form {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        input, select, button {
            padding: 8px;
            font-size: 1rem;
        }
        button {
            cursor: pointer;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<header>
    <h1>Система принятия решений</h1>
</header>
<main>
    <section>
        <h2>Добавить инцидент</h2>
        <form method="post" action="/incidents">
            <input type="text" name="description" placeholder="Описание" required>
            <select name="critical">
                <option value="false">Не критичный</option>
                <option value="true">Критичный</option>
            </select>
            <select name="reportedBy">
                <#list users as user>
                    <option value="${user.id}">${user.username}</option>
                </#list>
            </select>
            <button type="submit">Добавить</button>
        </form>

        <h2>Инциденты</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Описание</th>
                <th>Состояние</th>
                <th>Критичный</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list incidents as incident>
                <tr>
                    <td>${incident.id}</td>
                    <td>${incident.description}</td>
                    <td>${incident.currentState}</td>
                    <td>${incident.critical?string("Да", "Нет")}</td>
                    <td>
                        <form method="post" action="/incidents/${incident.id}/event">
                            <input type="hidden" name="event" value="CONFIRM_ALERT">
                            <button type="submit">Подтвердить</button>
                        </form>
                        <form method="post" action="/incidents/${incident.id}/event">
                            <input type="hidden" name="event" value="CANCEL_ALERT">
                            <button type="submit">Отменить</button>
                        </form>
                        <form method="post" action="/incidents/${incident.id}/event">
                            <input type="hidden" name="event" value="DECIDE_EVACUATION">
                            <button type="submit">Эвакуировать</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </section>
    <section>
        <h2>Пользователи</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Имя пользователя</th>
                <th>Роль</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.role}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>
