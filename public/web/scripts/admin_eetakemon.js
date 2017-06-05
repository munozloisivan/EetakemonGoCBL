$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/eetakemon/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td><img class='img-responsive' src='"+json[i].imagen+"' alt='"+json[i].nombre+"' style='width: 50px;height: 50px;' align='center'></td>");
            tr.append("<td>" + json[i].habilidad + "</td>");
            tr.append("<td>" + json[i].descripcion + "</td>");
            tr.append("<td>" + json[i].tipo+ "</td>");
            tr.append("<td><button type='button' onclick='settingsEetakemon(this.id)' id='settings' class='btn btn-warning btn-sm'><span class='glyphicon glyphicon-wrench'></span></td>");
            $('table').append(tr);
            $("#settings").attr('id',json[i].id);
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

function settingsEetakemon(id) {
    var settingsID = document.getElementById(id).id;
    window.location.href="admin_settingsEetakemon.html?id="+settingsID;
}

function addEetakemon() {
    window.location.href="admin_addEetakemon.html";
}