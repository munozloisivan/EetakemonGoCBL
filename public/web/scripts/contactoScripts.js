
/**
 * Created by Dani on 05/06/2017.
 */
$(document).ready(function() {
    function sendMail() {
        var link = "mailto:eetakemongocbl@gmail.com"
                + "?cc=danimb93@gmail.com"
                + "&subject=" +  encodeURI("Ticket usuario")
                + "&body=" +  encodeURI(document.getElementById('mensaje').value);

        window.location.href = link;
    }
})
















