$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/eetakemon/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].habilidad + "</td>");
            tr.append("<td>" + json[i].tipo + "</td>");
            tr.append("<td>" + json[i].id+ "</td>");
            tr.append("<td><button type='button' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            $('table').append(tr);
            $("#delete").attr('id',json[i].id);
        }
    })
})

function filterEetakemon() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableEetakemon");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function reloadUserLoged(){
    window.location.href="userLoged.html?email="+sessionStorage.emailLoged;
}

