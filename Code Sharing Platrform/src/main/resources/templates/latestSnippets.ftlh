<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Code Sharing Platform</title>

  <style type="text/css">
        <#include "./css/mainCss.css">
        <#include "./css/latestSnippets.css">
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
        <h2>Latest code snippets</h2>
        <#list codeSnippets as codeSnippet>
            <div class = "snippet">
              <span class = "dateTime">${codeSnippet.lastUpdateTime}</span>
              <br>
              <pre class = "code"><code>${codeSnippet.code}</code></pre>
            </div>
        </#list>
  </section>
</div>

<footer id="main-footer">
  <p>Copyright &2022; <a href="https://github.com/NikSsh">NikSsh</a></p>
</footer>
</body>
</html>