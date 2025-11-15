
const lastEventId = document.querySelector("meta[name='_lastEventId']").content;

const evtSource = new EventSource(`userStream?lastEventId=${lastEventId}`);
const formEl = document.querySelector("form");

evtSource.addEventListener("USER", (event) => {
  console.log("GOT USER");
  const newElement = document.createElement("li");
  const eventList = document.getElementById("list");

  newElement.textContent = `message: ${event.lastEventId} ${event.data}`;
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