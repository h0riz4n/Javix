let tg = window.Telegram.WebApp;
tg.expand();

document.addEventListener("click", (event) => {
  if (event.target.className == "header__cancel-icon") {
    tg.close();
  }
});
