
$(document).ready(function() {

    $.get( "http://localhost:8080/myapp/usuario/got_email/"+sessionStorage.emailLoged , function( data ) {
        $("#userLogedNick").html(" "+data.nick);
        $("#nombreLoged").append(data.nombre);
        $("#usuarioLoged").append(data.nick);
        $("#correoLoged").append(data.email);
        $("#nivel").append(data.nivel);
        $("#experiencia").append(data.experiencia);
        sessionStorage.nickLoged = data.nick;
        sessionStorage.idLoged = data.id;
        sessionStorage.adminLoged = data.admin;
    });

    if (sessionStorage.adminLoged==1){
        $("#admin_button").show();
    }
    else{
        $("#admin_button").hide();
    }

    $("#close_button").click(function () {
        window.location.href="index.html";
    })
})

function loadUserEetakemon() {
    window.location.href="userEetakemon.html?id="+userLogedID;
}