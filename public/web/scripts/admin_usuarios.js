$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/usuario/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nick + "</td>");
            tr.append("<td>" + json[i].email + "</td>");
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].id+ "</td>");
            tr.append("<td><button type='button' onclick='settingsUser(this.id)' id='delete' class='btn btn-warning btn-sm'><span class='glyphicon glyphicon-wrench'></span></td>");
            $('table').append(tr);
            $("#delete").attr('id',json[i].id);
        }
    })
})

function settingsUser(id) {
    var settingsID = document.getElementById(id).id;
    window.location.href="admin_settingsUsuario.html?id="+settingsID;
}

function filterUsers() {
    var input, filter, table, tr, td, td2, td3, i;
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableUsuarios");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        td2 = tr[i].getElementsByTagName("td")[1];
        td3 = tr[i].getElementsByTagName("td")[2];
        if ((td)||(td2)||(td3)) {
            if ((td.innerHTML.toUpperCase().indexOf(filter) > -1)||(td2.innerHTML.toUpperCase().indexOf(filter) > -1)||(td3.innerHTML.toUpperCase().indexOf(filter) > -1)) {
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