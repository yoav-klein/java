
const evtSource = new EventSource("chat");

evtSource.addEventListener("CHAT", (event) => {  
  const newElement = document.createElement("li");
  const eventList = document.getElementById("list");

  newElement.textContent = `message: ${event.data}`;
  eventList.appendChild(newElement);
});