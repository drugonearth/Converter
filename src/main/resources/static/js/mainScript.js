$('#convertButton').change(function() {
    $.post(
        '/convert', {convert: $('#inputBox').val()},
        function (data){
            const fieldNameElement = document.getElementById('contentOutput');
            fieldNameElement.innerHTML = data;
        }
    )
});

// $('#inputBox').bind(function() {
//     timerId = setTimeout(function () {
//         $.post(
//             '/convert', {convert: $('#inputBox').val()},
//             function (data) {
//                 const fieldNameElement = document.getElementById('contentOutput');
//                 fieldNameElement.innerHTML = data;
//             }
//         )
//     }, 2000);
// });