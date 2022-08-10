function redirectToChosenSnippet() {
    let snippet_id = document.getElementById("chosenSnippetIdInput").value;
    alert(snippet_id);
    window.location.assign('http://localhost:8088/code/' + snippet_id);
}

function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": parseInt(document.getElementById("time_restriction").value),
        "views": parseInt(document.getElementById("views_restriction").value)
    };


    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
        alert(xhr.responseText);
        //alert("Success!");
    }
}