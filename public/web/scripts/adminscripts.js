/**
 * Created by ivanm on 20/05/2017.
 */

$(document).ready(function () {

    $.getJSON("http://localhost:8080/myapp/etakemon/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].habilidad + "</td>");
            tr.append("<td>" + json[i].tipo + "</td>");
            tr.append("<td>" + json[i].id + "</td>");
            tr.append("<td><button type='delete' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            tr.append("<td><button type='submit' id='modify' class='btn btn-warning btn-xs'>Modificar</button></td>");
            $('table').append(tr);
        }
    })

    
    $.getJSON("http://localhost:8080/myapp/logros/get_all", function (json) {
        var trr;
        for(var j = 0; j < json.length; j++){
            trr = $('<tr/>');
            trr.append("<td>" + json[i].nombre + "</td>");
            trr.append("<td>" + json[i].descripcion + "</td>");
            trr.append("<td>" + json[i].experiencia + "</td>");
            trr.append("<td>" + json[i].id + "</td>");
            trr.append("<td><button type='delete' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            trr.append("<td><button type='submit' id='modify' class='btn btn-warning btn-xs'>Modificar</button></td>");
            $('table').append(trr);
        }
    })
    
})

function etakemonsFunction() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("EtakemonFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableEtakemons");
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

function logrosFunction() {
    var input, filter, table, trr, td, j;
    input = document.getElementById("LogrosFilter");
    filter = input.value.toUpperCase();
    table = document.getElementById("tableLogros");
    tr = table.getElementsByTagName("trr");
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