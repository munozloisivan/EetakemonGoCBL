$(document).ready(function() {

    $("#userLogedNick").html(" "+sessionStorage.nickLoged);

    $.getJSON("/myapp/usuario/"+sessionStorage.emailLoged+"/objetos", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].descripcion+ "</td>");
            tr.append("<td>" + json[i].id + "</td>");
            $('table').append(tr);
        }
    })
})

function filterObjetosUsuario() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableObjetosUsuario");
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