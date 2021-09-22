document.querySelector('.historyTable').innerHTML = '<table id = "historyTable" class="messages"></table>'
let tble = document.querySelector('.historyTable')
window.addEventListener("load", function(){
    $.get(
        '/historyTable',
        function (data) {
            let messages = JSON.parse(data)
            for(let i = 0; messages.length; i++)
            {
                let row = document.createElement('tr')
                row.innerHTML += '<td id="messageId" style = "display:none;">'+ messages[i].id + '</td>'
                row.innerHTML += '<td>'+ messages[i].inputString + '</td>'
                row.innerHTML += '<td>'+ messages[i].convertedString + '</td>'

                let button = document.createElement('button')
                button.appendChild(document.createTextNode("Удалить"))
                let evnt = document.createAttribute('onclick')
                evnt.value = 'delRow(this)'
                button.setAttributeNode(evnt)

                row.appendChild(button)
                tble.appendChild(row)
            }
        }
    )
});

function delRow(r)
{
    $.post(
        '/deleteFromHistory', {id: r.parentNode.childNodes[0].childNodes[0].nodeValue},
    )
    console.log(r.parentNode.childNodes[0].childNodes[0].nodeValue)
    r.parentNode.remove();
}