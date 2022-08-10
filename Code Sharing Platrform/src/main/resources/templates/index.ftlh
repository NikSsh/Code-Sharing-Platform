<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code Sharing Platform</title>
    <style type="text/css">
            <#include "./css/mainCss.css">
    </style>

    <script>
        <#include "./js/mainScripts.js">
    </script>
</head>
<body>
    <header id="main-header">
        <div class="container">
            <h1>Code Sharing Platform</h1>
        </div>
    </header>

    <nav id="navbar">
        <div class="container">
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/code/new">Create new code snippet</a></li>
                <li><a href="/code/latest">Latest non restricted code snippets</a></li>
                <li>>
                    <form>
                        <label>Find code snippet by id:</label>
                        <input id="chosenSnippetIdInput" type="text">
                        <button type="submit" onclick="redirectToChosenSnippet()">Find</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>

    <section id="showcase">
        <div class="container">
            <h1>WELCOME!</h1>
            <h2>This platform provides the ability to share your code with friends or a team and even set some restrictions</h2>
        </div>
    </section>

    <div class = "container">
        <img class = "customImages" src = "./img/team2.png" alt="team2">
        <img class = "customImages" src = "./img/teamnew.jpg" alt="teamnew">
    </div>


    <footer id="main-footer">
        <p>Copyright &2022; <a href="https://github.com/NikSsh">NikSsh</a></p>
    </footer>
</body>
</html>