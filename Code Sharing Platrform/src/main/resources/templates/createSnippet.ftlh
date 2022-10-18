<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Code Sharing Platform</title>
  <style type="text/css">
        <#include "./css/mainCss.css">
        <#include "./css/CreateSnippet.css">
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
              <form action="/code">
                  <label>Find code snippet by id:</label>
                  <input name="UUID" id="chosenSnippetIdInput" type="text">
                  <button type="submit" value="Find"></button>
              </form>
          </li>
      </ul>
    </div>
  </nav>

  <section id="showcase">
    <h2>Create code snippet</h2>
    <div class="createSnippet">
      <textarea id="code_snippet">// write your code here</textarea>
      <Label class ="snippet-input_label">Input time restriction</Label>
      <input id="time_restriction" type="text"/>
      <Label class ="snippet-input_label">Input views restriction</Label>
      <input id="views_restriction" type="text"/>
      <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </div>
  </section>
</div>

<footer id="main-footer">
  <p>Copyright &2022; <a href="https://github.com/NikSsh">NikSsh</a></p>
</footer>
</body>
</html>