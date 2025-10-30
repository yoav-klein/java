
const sumbitButton = document.querySelector('#submit-button');
const nameInput = document.querySelector('#username');
const ageInput = document.querySelector('#age');
const list = document.querySelector('ul');


function populateList() {
    const promise = fetch("users");
    list.innerHTML = '';
    promise.then(response => response.json()).then(data =>  data.forEach(user => {
        const item = document.createElement('li');
        item.innerText = user.name + " " + user.age;
        list.appendChild(item);
    }));
}

sumbitButton.addEventListener('click', () => {
    const promise = fetch("user", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: nameInput.value, age: ageInput.value }),
    });

    promise.then(response => populateList());
});
