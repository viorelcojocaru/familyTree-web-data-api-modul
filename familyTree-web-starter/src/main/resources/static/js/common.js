function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active ";
}



/*$(document).ready(function () {
   $('.nBtn, .eBtn').on('click',function (event) {
        event.preventDefault();
        var href=$(this).attr('href');
        var text=$(this).text();
        if (text=='Edit') {
            $.get(href, function (person) {
                ${'.personForm #id'}.val(person.id);
                ${'.personForm #firstName'}.val(person.firstName);
                ${'.personForm #lastName'}.val(person.lastName);
                ${'.personForm #lastNameOnBirth'}.val(person.lastNameOnBirth);
                ${'.personForm #birthDate'}.val(person.birthDate);
                ${'.personForm #deathDate'}.val(person.deathDate);
            });
        }else {
            ${'.personForm #id'}.val('');
            ${'.personForm #firstName'}.val('');
            ${'.personForm #lastName'}.val('');
            ${'.personForm #lastNameOnBirth'}.val('');
            ${'.personForm #birthDate'}.val('');
            ${'.personForm #deathDate'}.val('');
        }

       $('.personForm').modal();   })
});*/
