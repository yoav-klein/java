let menuButtonEl = document.querySelector(".menu-button");
let menuContentEl = document.querySelector(".menu-content");

menuButtonEl.addEventListener('click', () => {
    if(menuContentEl.hasAttribute('hidden')) {
        menuContentEl.removeAttribute('hidden')
    } else {
        menuContentEl.setAttribute('hidden', '')
    }
});

  
  // Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.menu-button')) {
        if(!menuContentEl.hasAttribute('hidden')) {
            menuContentEl.setAttribute('hidden', '')
        }
    }
}