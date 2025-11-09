
const evtSource = new EventSource("userStream");
const formEl = document.querySelector("form");


evtSource.addEventListener("USER", (event) => {  
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
    headers.append("Content-Type", "application/json");
    const responsePromise = fetch("user", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(data)
    });
    responsePromise.then(resp => console.log(`OK: ${resp.ok}`)).catch(e => console.log(`error: ${e}`));

});