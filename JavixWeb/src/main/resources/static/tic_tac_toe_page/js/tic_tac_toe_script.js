import { GameEngine } from "./bot_tic_tac_toe.js";
import { GameEngineFriends } from "./friends_tic_tac_toe.js";

let game;

document.addEventListener("click", (event) => {

    if (event.target.className == "button-solo") {
        game = new GameEngine();
    }

    if (event.target.className == "button-two-players") {
        game = new GameEngineFriends();
    }

});