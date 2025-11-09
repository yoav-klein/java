
const evtSource = new EventSource("chat");
const formEl = document.querySelector("form");


evtSource.addEventListener("CHAT", (event) => {  
  const newElement = document.createElement("li");
  const eventList = document.getElementById("list");

  newElement.textContent = `message: ${event.data}`;
  eventList.appendChild(newElement);
});

formEl.addEventListener("submit", (event) => {
    event.preventDefault(); // prevent page reload

    const formData = new FormData(formEl);
    formEl.reset();
    const data = Object.fromEntries(formData.entries()); // convert to plain object

    const headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    const responsePromise = fetch("chat", {
        method: "POST",
        headers: headers,
        body: `message=${data['message']}`
    });
    responsePromise.then(resp => console.log(`OK: ${resp.ok}`)).catch(e => console.log(`error: ${e}`));

});