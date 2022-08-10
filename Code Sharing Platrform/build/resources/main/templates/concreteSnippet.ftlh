<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Code Sharing Platform</title>
  <style type="text/css">
          <#include "./css/mainCss.css">
          <#include "./css/concreteSnippet.css">
    </style>

    <script>
          <#include "./js/mainScripts.js">
    </script>
</head>
<body>

<div id="wrap">
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
                <li>
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
        <h2>Your code snippet</h2>
        <div class="snippet">
            <span id = "dateTime">${codeSnippet.lastUpdateTime}</span>

            <#if codeSnippet.restriction == "TIME_VIEWS_RESTRICTION" || codeSnippet.restriction == "VIEWS_RESTRICTION">
                <p><span id="views_restriction">${codeSnippet.accessViews}</span> more views allowed</p>
            </#if>

            <#if codeSnippet.accessTime != 0 >
                <p>The code will be available for <span id="time_restriction">${codeSnippet.accessTime}</span> seconds</p>
            </#if>

            <pre id = "code"><code>${codeSnippet.code}</code></pre>
        </div>
  </section>
</div>

<footer id="main-footer">
  <p>Copyright &2022; <a href="https://github.com/NikSsh">NikSsh</a></p>
</footer>
</body>
</html>