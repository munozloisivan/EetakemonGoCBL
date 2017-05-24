$(document).ready(function() {

    $.getJSON("http://localhost:8080/myapp/usuario/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nick + "</td>");
            tr.append("<td>" + json[i].email + "</td>");
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].id+ "</td>");
            tr.append("<td><button type='button' onclick='deleteUser(this.id)' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            $('table').append(tr);
            $("#delete").attr('id',json[i].id);
        }
    })
})

function deleteUser(id) {

    $.get( "http://localhost:8080/myapp/usuario/delete/"+id , function() {
        alert("Usuario eliminado");
        location.reload();
    });

}

function filterUsers() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableUsuarios");
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
