<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand d-flex align-items-center" href="/">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none"
                 stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                 class="me-2" viewBox="0 0 24 24">
                <rect x="5" y="10" width="14" height="10" rx="2" ry="2"/>
                <path d="M8 10V6a4 4 0 1 1 8 0v4"/>
            </svg>
            Система принятия решений
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <#if role??>
                    <#switch role>
                        <#case "ROLE_OPERATOR">
                            <li class="nav-item"><a class="nav-link" href="/">Заявки</a></li>
                            <li class="nav-item"><a class="nav-link" href="/">Профиль</a></li>
                            <#break>
                        <#case "ROLE_MONITOR">
                            <li class="nav-item"><a class="nav-link" href="/">Заявки</a></li>
                            <li class="nav-item"><a class="nav-link" href="/">Профиль</a></li>
                            <#break>
                        <#case "ROLE_SUPERVISOR">
                            <li class="nav-item"><a class="nav-link" href="/">Заявки</a></li>
                            <li class="nav-item"><a class="nav-link" href="/">Профиль</a></li>
                            <li class="nav-item"><a class="nav-link" href="/">Отчёты</a></li>
                            <#break>
                        <#case "ROLE_ADMIN">
                            <li class="nav-item"><a class="nav-link" href="/">Пользователи</a></li>
                            <li class="nav-item"><a class="nav-link" href="/">Отчёты</a></li>
                            <#break>
                    </#switch>
                    <li class="nav-item"><a class="nav-link text-danger" href="/logout">Выход</a></li>
                <#else>
                    <li class="nav-item"><a class="nav-link" href="/login">Войти</a></li>
                    <li class="nav-item"><a class="nav-link" href="/registration">Регистрация</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>

<!-- Bootstrap 5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
