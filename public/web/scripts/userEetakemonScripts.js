$(document).ready(function() {

    $("#userLogedNick").html(" "+sessionStorage.nickLoged);

    $.getJSON("/myapp/usuario/"+sessionStorage.idLoged+"/get_capturas", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nombreetakemon + "</td>");
            tr.append("<td><img class='img-responsive' src='"+json[i].imagen+"' alt='"+json[i].nombreetakemon+"' style='width: 50px;height: 50px;' align='center'></td>");
            tr.append("<td>" + json[i].habilidadetakemon + "</td>");
            tr.append("<td>" + json[i].nivel + "</td>");
            tr.append("<td>" + json[i].fecha+ "</td>");
            tr.append("<td><button type='delete' onclick='deleteThisCaptura(this.id)' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            $('table').append(tr);
            $("#delete").attr('id',json[i].id);
        }
    })
})

function filterEetakemonUsuario() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("nameFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableEetakemonUsuario");
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

function deleteThisCaptura(id) {

    swal({
            title: "¿Estas seguro?",
            text: "Eliminar una Captura es una acción irreversible.",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Borrar",
            cancelButtonText: "Atrás",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function(isConfirm) {
            if (isConfirm) {
                $.get( "/myapp/captura/delete/"+id , function() {
                    window.location.href="userEetakemon.html"
                })
            } else {
                swal("Cancelado", "¡Se ha cancelado el proceso de eliminación!", "error");
            }
        });

}

function reloadUserLoged(){
    window.location.href="userLoged.html?email="+sessionStorage.emailLoged;
}