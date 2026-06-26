

let messages;
const prom = fetch('messages');
prom.then(resp => resp.json()).then(data => messages = data);

const pEl = document.getElementById('message');
document.getElementById('generate-message').addEventListener('click', () => {
    console.log('click');
    pEl.innerText = messages['hello'];
});