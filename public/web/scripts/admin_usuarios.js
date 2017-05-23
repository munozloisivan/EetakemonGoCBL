/**
 * Created by root on 21/05/17.
 */

$(document).ready(function() {
    $.getJSON("http://localhost:8080/myapp/usuario/get_all", function (json) {
        var tr;
        for (var i = 0; i < json.length; i++) {
            tr = $('<tr/>');
            tr.append("<td>" + json[i].nick + "</td>");
            tr.append("<td>" + json[i].email + "</td>");
            tr.append("<td>" + json[i].nombre + "</td>");
            tr.append("<td>" + json[i].id + "</td>");
            tr.append("<td><button type='delete' data-toggle='tooltip' title='delete' id='delete' class='btn btn-danger btn-xs'>X</button></td>");
            tr.append("<td><button type='button' data-toggle='tooltip' title='edit' class='btn btn-default btn-xs'><span class='glyphicon glyphicon-edit'></span></button></td>");
            $('table').append(tr);
        }
    })

    $("#editar_button").click(function () {
        var data = {
            nick: $("#nick").val(),
            newnick: $("#newnick").val(),
            newpassword: $("#newpassword").val()
        };
        if (inputsNotEmpty(".editUser")) {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/myapp/usuario/edit/",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: [
                    function (response) {
                        if (data.) {
                            sessionStorage.setItem("userIdCurrentlyLoggedIn", data.id);
                            sessionStorage.setItem("isAdmin", data.isadmin);
                            window.location.replace("/forms/menu.html");
                        } else {
                            alert("User or Password not registered, please register first or try again");
                        }
                    }
                ],
                error: [
                    function (request, status) {
                        alert(status);
                    }
                ]
            });
        } else {
            alert("Fill in all the required fields, please");
        }
        function inputsNotEmpty(classToBeChecked) {
            var isReadyToSubmit = true;
            $(classToBeChecked).each(function () {
                if (isEmpty($(this).val())) {
                    isReadyToSubmit = false;
                }
            });
            return isReadyToSubmit;
        }

        function isEmpty(valueToBeChecked) {
            return valueToBeChecked.length <= 0;
        }
    })

});


function myFunction() {
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
