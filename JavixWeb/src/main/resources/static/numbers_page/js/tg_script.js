let tg = window.Telegram.WebApp;
tg.expand();

const url = new URL(window.location.href);
const playerID = url.searchParams.get('id') ?? '0';
console.log(playerID);

document.addEventListener("click", (event) => {
  if (event.target.className == "header__cancel-icon") {
    tg.close();
  }
});   
