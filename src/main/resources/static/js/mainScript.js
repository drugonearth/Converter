const outputField = document.getElementById('contentOutput');

$('#inputBox').on('input',function() {
    let timerId = setTimeout(function () {
        $.post(
            '/convert', {convert: $('#inputBox').val()},
            function (data) {
                outputField.innerHTML = data;
            }
        )
    }, 1500);
});

$('#convertButton').on('click',function() {
    $.post(
        '/addToHistory', {inputText: $('#inputBox').val(),
            outputText: outputField.innerHTML}
    )
});